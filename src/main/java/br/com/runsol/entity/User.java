package br.com.runsol.entity;

import java.util.Date;

public class User {
    private String username;
    private String firstName;
    private String lastName;
    private Date tsCreation;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getTsCreation() {
        return tsCreation;
    }

    public void setTsCreation(Date tsCreation) {
        this.tsCreation = tsCreation;
    }
}
