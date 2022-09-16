package Day2;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "family_Name")
    private String familyName;
    @Column
    private LocalDate birthday;
    @Column
    private String email;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "phone_id")
    private Phone phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private List<Task> task;

    @ManyToMany
    @JoinTable(
            name = "Employyee_Project",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private List<Project> projects = new ArrayList<>();

    public Employee() {
    }

    public Employee(String firstName, String familyName, LocalDate birthday, String email) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.birthday = birthday;
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }


    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
            //    ", phone=" + phone +
                ", task=" + task +
                ", projects=" + projects +
                '}';
    }
}
