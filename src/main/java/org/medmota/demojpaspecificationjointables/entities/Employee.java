package org.medmota.demojpaspecificationjointables.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

@Entity
@Table(name = "my_employee")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "MY_EMPLOYEE_ID")
    @JsonManagedReference
    private Set<Hobby> hobbies;

    public Employee(String firstName, String lastName, Integer age, Set<Hobby> hobbies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.hobbies = hobbies;
    }

}
