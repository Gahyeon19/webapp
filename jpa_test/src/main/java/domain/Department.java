package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dept")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mysql에서 id 만들어서 제공 (integer이어야 함)
    private int deptId;
    @Column(length = 10, nullable = false)
    private String deptName;
    @OneToMany(mappedBy = "department")     //Employee 에서 설정한 필드 이름. mappedBy가 있으면 연관관계 주인이 아님.
    List<Employee> emps = new ArrayList<Employee>();

    public List<Employee> getEmps() {
        return emps;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}
