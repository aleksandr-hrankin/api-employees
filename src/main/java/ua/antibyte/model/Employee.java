package ua.antibyte.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;
    private String gender;
    private String title;
    private String first;
    private String last;
    private String email;
    private LocalDate dob;
    private int age;
    private String phone;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "picture_id")
    private Picture picture;
}
