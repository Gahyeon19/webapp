package jpajava;

import domain.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DepartmentFindTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            System.out.println("비영속 상태");

            Department dept1 = em.find(Department.class, 1);
            System.out.println(dept1.getDeptName());

            System.out.println("커밋 전");
            tx.commit();
            System.out.println("커밋 후");

        } catch (Exception e) {
            tx.rollback();
        }

    }
}
