package com.hotmail.viktorz08.lab1b.repository;

import com.hotmail.viktorz08.lab1b.repository.entities.Employers;

import java.util.List;


/**
 * Created by Victor on 11/19/2014.
 */

public interface EmployerDataRepository {

    List<Employers> getEmployersData();

    void addEmployerData(Employers data);
}
