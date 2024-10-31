package jpajava;

import domain.Department;
import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Emp_Dept_loop_test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        System.out.println("START TRANSACTION");
        try {
            Employee employee = em.find(Employee.class, "202403");
            System.out.println("EMP NAME : " + employee.getEmpName());   // Department 와 Employee 모두에 toString 구현하면 무한 루프에 빠짐.
            System.out.println("DEPT NAME : " + employee.getDepartment().getDeptName());


            System.out.println("BEFORE COMMIT");
            tx.commit();
            System.out.println("AFTER COMMIT");
        } catch (Exception e) {
            tx.rollback();
        }
        System.out.println("END TRANSACTION");
    }
}
