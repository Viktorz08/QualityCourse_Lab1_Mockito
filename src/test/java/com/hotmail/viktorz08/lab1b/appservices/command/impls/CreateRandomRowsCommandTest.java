package com.hotmail.viktorz08.lab1b.appservices.command.impls;

import com.hotmail.viktorz08.lab1b.appservices.command.Command;
import com.hotmail.viktorz08.lab1b.repository.EmployerDataRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CreateRandomRowsCommandTest {

    @Test
    public void testExecuteZero() throws Exception {
        CreateRandomRowsCommand instance = new CreateRandomRowsCommand();
        instance.setRandom(new Random(0));

        EmployerDataRepository repo = mock(EmployerDataRepository.class);
        instance.repository = repo;

        instance.execute();

        verify(repo,times(0)).addEmployerData(anyObject());
    }

    @Test
    public void testExecuteHundred() throws Exception {
        CreateRandomRowsCommand instance = new CreateRandomRowsCommand();
        instance.setRandom(new Random(0));

        EmployerDataRepository repo = mock(EmployerDataRepository.class);
        instance.repository = repo;
        instance.setRandomRowCount(100);

        instance.execute();

        verify(repo, times(100)).addEmployerData(anyObject());
    }

    @Test
    public void testExecute() throws Exception {
        CreateRandomRowsCommand instance = new CreateRandomRowsCommand();
        instance.setRandom(new Random(0));

        EmployerDataRepository repo = mock(EmployerDataRepository.class);
        instance.repository = repo;
        instance.setRandomRowCount(2);

        instance.execute();

        verify(repo,times(2)).addEmployerData(anyObject());
    }
}