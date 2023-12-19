package com.afb.template.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@Table(name = "citizen")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Citizen extends Users{
    @Column(name = "alias")
    private String alias;

    @Column(name = "birthday_date")
    private Date birthdayDate;
}
