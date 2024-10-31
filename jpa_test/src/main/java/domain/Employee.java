package domain;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id //primary key
    @Column(name="emp_id", length = 6)  // DB에서의 컬럼명 지정
    private String empId;
    @Column(length = 10, nullable = false)
    private String empName;
//    @OneToOne     // fetch type : EAGER
//    @OneToMany      // fetch type : LAZY    (LAZY : 지연 로딩)
    @ManyToOne(fetch = FetchType.EAGER)    // fetch type : EAGER     (EAGER : 즉시 로딩)
    @JoinColumn(name = "dept_id")   // FK 알려주기. Foreign Key를 갖고 있는 쪽이 연관관계 주인.
    private Department department;
    private String joinDate;
    private long salary;

    public Employee() {}

    public Employee(String empId, String empName, Department department, String joinDate, long salary) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.joinDate = joinDate;
        this.salary = salary;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", department=" + department +
                ", joinDate='" + joinDate + '\'' +
                ", salary=" + salary +
                '}';
    }
}
