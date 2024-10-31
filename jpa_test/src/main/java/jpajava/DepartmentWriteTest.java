package jpajava;

import domain.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DepartmentWriteTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // 지연 쓰기 X (persist 할때마다 insert)
        tx.begin();
        try {
            System.out.println("비영속 상태");
            Department dept1 = new Department();
            dept1.setDeptName("AA");
            Department dept2 = new Department();
            dept2.setDeptName("BB");
            Department dept3 = new Department();
            dept3.setDeptName("CC");

            System.out.println("CREATE dep1");
            em.persist(dept1);
            System.out.println("CREATE dep2");
            em.persist(dept2);
            System.out.println("CREATE dep3");
            em.persist(dept3);

            System.out.println("커밋 전");
            tx.commit();
            System.out.println("커밋 후");

        } catch (Exception e) {
            tx.rollback();
        }

    }
}
