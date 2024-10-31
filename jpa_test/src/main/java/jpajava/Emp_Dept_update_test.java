package jpajava;

import domain.Department;
import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Emp_Dept_update_test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        System.out.println("START TRANSACTION");
        try {
            Department dept = new Department();     // 비영속. 즉시 쓰기
            dept.setDeptName("IT");
            em.persist(dept);                       // 영속
            System.out.println("CREATE DEPT");

            Employee emp = em.find(Employee.class, "202402");
            System.out.println("EMP - DB에서 가져옴");
            emp.setDepartment(dept);

            em.persist(emp);

            System.out.println("BEFORE COMMIT");
            tx.commit();
            System.out.println("AFTER COMMIT");
        } catch (Exception e) {
            tx.rollback();
        }
        System.out.println("END TRANSACTION");
    }
}
