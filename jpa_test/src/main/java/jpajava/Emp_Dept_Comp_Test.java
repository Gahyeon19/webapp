package jpajava;

import domain.Company;
import domain.Department;
import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Emp_Dept_Comp_Test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        System.out.println("START TRANSACTION");
        try {
//            Company company = new Company();
//            company.setName("kosta");
//            em.persist(company);
//            System.out.println("COMPANY 생성");
//
//            Department dept = new Department();     // 비영속. 즉시 쓰기
//            dept.setDeptName("Sales");
//            dept.setCompany(company);
//            em.persist(dept);
//            System.out.println("DEPT 생성");

            Employee emp = em.find(Employee.class, "202402");
            System.out.println(emp.getEmpName());
            System.out.println("DEPT CLASS");
            System.out.println(emp.getDepartment().getClass());     //class domain.Department$HibernateProxy$4UDvFcY1
            System.out.println("DEPT ID");
            System.out.println(emp.getDepartment().getDeptId());
            System.out.println("DEPT NAME");
            System.out.println(emp.getDepartment().getDeptName());



            System.out.println("BEFORE COMMIT");
            tx.commit();
            System.out.println("AFTER COMMIT");
        } catch (Exception e) {
            tx.rollback();
        }
        System.out.println("END TRANSACTION");
    }
}
