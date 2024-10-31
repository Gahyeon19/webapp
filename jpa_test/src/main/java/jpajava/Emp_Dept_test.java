package jpajava;

import domain.Department;
import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Emp_Dept_test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        System.out.println("START TRANSACTION");
        try {
            Department dept = new Department();     // 비영속. 즉시 쓰기
            dept.setDeptName("IT");
            System.out.println("CREATE DEPT");
            em.persist(dept);                       // 영속

            Employee emp = new Employee();          // 비영속. 지연 쓰기
            emp.setEmpId("202403");
            emp.setEmpName("김길동");
            emp.setDepartment(dept);
            emp.setJoinDate("2021-01-01");
            emp.setSalary(100);
            em.persist(emp);                        // 영속
//            dept.getEmps().add(emp);

            emp = new Employee();          // 비영속. 지연 쓰기
            emp.setEmpId("202404");
            emp.setEmpName("김연우");
            emp.setDepartment(dept);
            emp.setJoinDate("2022-01-01");
            emp.setSalary(1000);
            em.persist(emp);                        // 영속
//            dept.getEmps().add(emp);        // 순수 객체 상태를 고려해서 사용하려면 ~~

//            Department findDept = em.find(Department.class, dept.getDeptId());
//            System.out.println("IT 부서의 직원 수 : " + findDept.getEmps().size());

            System.out.println("BEFORE COMMIT");
            tx.commit();
            System.out.println("AFTER COMMIT");
        } catch (Exception e) {
            tx.rollback();
        }
        System.out.println("END TRANSACTION");
    }
}
