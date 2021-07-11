package com.inflearn.springbootwithjpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
}
