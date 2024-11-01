package jpajava;

import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Emp_Dept_Comp_JPQL_Test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        System.out.println("START TRANSACTION");
        try {
            //JPQL ==> N + 1 번의 쿼리 발생
//            String jpql = "select e from Employee e";   //객체 e를 가져옴    // select * from employee
//            List<Employee> emps = em.createQuery(jpql, Employee.class).getResultList();  //Employee.class 자리에는 쿼리의 결과로 받아올 타입을 작성

            //JPQL - fetch join (연관관계에 있는 객체까지 한번에 가져오는 쿼리)
            String fetchjpql = "select e from Employee e join fetch e.department";
            List<Employee> emps = em.createQuery(fetchjpql, Employee.class).getResultList();
            for (Employee emp : emps) {
                System.out.println(emp.getEmpName());
                System.out.println(emp.getDepartment().getDeptName());
                System.out.println("================");
            }

            System.out.println("BEFORE COMMIT");
            tx.commit();
            System.out.println("AFTER COMMIT");
        } catch (Exception e) {
            tx.rollback();
        }
        System.out.println("END TRANSACTION");
    }
}
