package com.hotmail.viktorz08.lab1b.repository.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Victor on 11/19/2014.
 */

//@Entity
//@Table(name = "EMPLOYERS")
public class Employers implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employerID;
    private String name;
    private String surname;
    private Date birthday;
    private double salary;

    public Employers(String name, String surname, Date birthday, double salary) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.salary = salary;
    }

    public Employers() {
        this("", "", null, 0);
    }

    public int getEmployerID() {
        return employerID;
    }

    public void setEmployerID(int employerID) {
        this.employerID = employerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("ID: ").append(employerID).append(" ");
        builder.append("Name: ").append(name);
        return builder.toString();
    }
}
