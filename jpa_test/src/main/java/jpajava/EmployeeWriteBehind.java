package jpajava;

import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeWriteBehind {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");   // persistence.xml의 unit name과 동일하게
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();     // transaction은 All or Nothing

        tx.begin();
        System.out.println("TRANSACTION STARTED");
        // 지연 쓰기 (commit 해야 insert)
        try {
            Employee emp1 = new Employee("202403", "손흥민3", null, "2024-01-01", 100000L);
            Employee emp2 = new Employee("202404", "손흥민4", null, "2024-01-01", 100000L);
            Employee emp3 = new Employee("202405", "손흥민5", null, "2024-01-01", 100000L);

            em.persist(emp1);
            em.persist(emp2);
            em.persist(emp3);

            System.out.println("BEFORE COMMIT");
            tx.commit();
            System.out.println("AFTER COMMIT");
        } catch (Exception e) {
            tx.rollback();
        }
    }
}
