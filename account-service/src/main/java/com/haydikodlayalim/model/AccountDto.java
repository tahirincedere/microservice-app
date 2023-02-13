package com.haydikodlayalim.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class AccountDto {

    private String id ;

    private String username;

    private String name;

    private String surname;

    private String email;

    private Date birthDate;

    public String getNameSurname() {
        return this.name + " " + this.surname;
    }
}