package com.atrdev.ecomapp.modules.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "users")
@NoArgsConstructor
//@AllArgsConstructor
public class User {
    //@EqualsAndHashCode.Include
    //@ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@EqualsAndHashCode.Include
    //@ToString.Include
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    //@EqualsAndHashCode.Include
    //@ToString.Include
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;
}
