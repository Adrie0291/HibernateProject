package Day2;

import javax.persistence.*;

@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(name = "phone_brand")
    @Enumerated(EnumType.STRING)
    private PhoneBrand brand;
    @Column
    private String number;

    public Phone() {
    }

    @OneToOne(mappedBy = "phone",fetch = FetchType.EAGER)
    private Employee employee;

    public Phone(PhoneBrand brand, String number) {
        this.brand = brand;
        this.number = number;
    }

    public void setBrand(PhoneBrand brand) {
        this.brand = brand;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "brand=" + brand +
                ", number='" + number+ '\'' +
                ", employee=" + employee +
                '}';
    }
}
