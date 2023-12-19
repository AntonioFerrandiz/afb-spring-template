package com.afb.template.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "admin")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Admin extends Users{
    @Column(name = "alias")
    private String alias;

}
