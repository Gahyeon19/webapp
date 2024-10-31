package jpajava;


import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeFindTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");   // persistence.xml의 unit name과 동일하게
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();     // transaction은 All or Nothing

        tx.begin();
        System.out.println("TRANSACTION STARTED");
        try {
            System.out.println("비영속 상태");
            System.out.println("DB에서 가져옴");
            Employee emp1 = em.find(Employee.class, "202402");
            Employee emp2 = em.find(Employee.class, "202402");
            System.out.println(emp1.getEmpName());
            System.out.println(emp2.getEmpName());
            System.out.println("emp1 == emp2 : " + (emp1 == emp2)); //true
            System.out.println("커밋 전");
            tx.commit();    // DB에 반영
            System.out.println("커밋 후");
//            System.out.println("1차 캐시에서 가져옴");
//            Employee emp3 = em.find(Employee.class, "202402");
//            System.out.println(emp3.getEmpName());

        } catch (Exception e) {
            tx.rollback();
        }
        System.out.println("TRANSACTION COMPLETED");

    }
}
