package Day3;

import javax.persistence.*;

@Entity
public class StudentBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String number;

    @OneToOne (mappedBy = "studentBook")
    private Student student;

    public StudentBook() {
    }
}
