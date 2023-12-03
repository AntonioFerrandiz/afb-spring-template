package com.afb.template.domain.model;

import com.afb.template.domain.enumeration.Rolname;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name = "role")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", unique = true, nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Rolname name;
}
