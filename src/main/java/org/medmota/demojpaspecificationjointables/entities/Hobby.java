package org.medmota.demojpaspecificationjointables.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "my_hobbies")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "HOBBY")
    private String hobby;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "MY_EMPLOYEE_ID")
    private Employee employee;

    public Hobby(String hobby){
        this.hobby = hobby;
    }
}
