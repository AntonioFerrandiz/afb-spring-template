package com.afb.template.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Admin extends Users{
    @Column(name = "alias")
    private String alias;

}
