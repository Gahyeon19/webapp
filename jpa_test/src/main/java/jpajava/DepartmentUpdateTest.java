package jpajava;

import domain.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DepartmentUpdateTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            System.out.println("비영속 상태");

//            Department dept1 = new Department();
//            dept1.setDeptName("DB");
//            em.persist(dept1);

            Department dept2 = em.find(Department.class, 4);
            System.out.println(dept2.getDeptId() + " " + dept2.getDeptName());

            dept2.setDeptName("HHRR");
            System.out.println(dept2.getDeptId() + " " + dept2.getDeptName());

            System.out.println("커밋 전");
            tx.commit();
            System.out.println("커밋 후");
        } catch (Exception e) {
            tx.rollback();
        }

    }
}
