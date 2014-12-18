package com.hotmail.viktorz08.lab1b.appservices.query.impls;

import com.hotmail.viktorz08.lab1b.appservices.command.impls.CreateRandomRowsCommand;
import com.hotmail.viktorz08.lab1b.repository.EmployerDataRepository;
import com.hotmail.viktorz08.lab1b.repository.entities.Employers;
import javafx.beans.binding.When;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

public class GetDuplicatesNameCommandTest {

    @Test
    public void testExecuteGet() throws Exception {
        GetDuplicatesNameCommand instance = new GetDuplicatesNameCommand();

        EmployerDataRepository repo = mock(EmployerDataRepository.class);
        instance.repository = repo;

        instance.execute();

        verify(repo, times(1)).getEmployersData();
    }

    @Test
    public void testExecuteWithCheck() throws Exception {
        GetDuplicatesNameCommand instance = new GetDuplicatesNameCommand();

        EmployerDataRepository repo = mock(EmployerDataRepository.class);

        List<Employers> employersList = new ArrayList<>();

        Random random = new Random(0);
        for (int i = 0; i < 10; i++) {
            Employers newData = new Employers();
            newData.setName("Name" + (random.nextInt(10)));
            newData.setSurname("Surname" + (random.nextInt(10)));
            String randDate = "1995-03-0" + ((random.nextInt(9) )+1 );
            newData.setBirthday(Date.valueOf(randDate));
            newData.setSalary(((double) (random.nextInt(10000))) / 100 + 1000);

            employersList.add(newData);
        }

        when(repo.getEmployersData()).thenReturn(employersList);
        instance.repository = repo;

        List<Employers> result = instance.execute();

        assertEquals(3, result.size());
    }

    @Test
    public void testExecuteWithCheck2() throws Exception {
        GetDuplicatesNameCommand instance = new GetDuplicatesNameCommand();

        EmployerDataRepository repo = mock(EmployerDataRepository.class);

        List<Employers> employersList = new ArrayList<>();

        Random random = new Random(1);
        for (int i = 0; i < 10; i++) {
            Employers newData = new Employers();
            newData.setName("Name1");
            newData.setSurname("Surname" + (random.nextInt(10)));
            String randDate = "1995-03-0" + ((random.nextInt(9) )+1 );
            newData.setBirthday(Date.valueOf(randDate));
            newData.setSalary(((double) (random.nextInt(10000))) / 100 + 1000);

            employersList.add(newData);
        }

        when(repo.getEmployersData()).thenReturn(employersList);
        instance.repository = repo;

        List<Employers> result = instance.execute();


        assertEquals(9, result.size());
    }
}