package com.inflearn.springbootwithjpa.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {


    private String name;

    private String city;
    private String zipcode;
}
