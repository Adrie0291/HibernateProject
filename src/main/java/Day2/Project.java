package Day2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "project_name")
    private String projectName;

    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees = new ArrayList<>();

    public Project() {
    }

    public Project(String projectName) {
        this.projectName = projectName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;


    }

    @Override
    public String toString() {
        return "Project{" +
                "projectName='" + projectName + '\'' +
                ", employees=" + employees +
                '}';
    }
}
