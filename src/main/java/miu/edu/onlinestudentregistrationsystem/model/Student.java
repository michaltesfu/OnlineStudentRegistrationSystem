package miu.edu.onlinestudentregistrationsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import miu.edu.onlinestudentregistrationsystem.Enum.InternationalStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @NotBlank(message = "Student Number Should Not Be Empty,Blank Or Null")
    @Column(unique = true)
    private String studentNumber;

    @NotBlank(message = "First Name Should Not Be Empty,Blank Or Null")
    private String firstName;
    private String middleName;

    @NotBlank(message = "Last Name Should Not Be Empty,Blank Or Null")
    private String lastName;

    private Double cgpa;

    @Column(nullable = false)
    private LocalDate enrollmentDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private InternationalStatus isInternational;
}
