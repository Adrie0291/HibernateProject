package Day3;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer Id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Date birthDate;

    @OneToOne
    @JoinColumn (name = "book_id")
    private StudentBook studentBook;

    public Student() {
    }
}

