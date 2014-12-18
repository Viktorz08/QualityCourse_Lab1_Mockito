package com.hotmail.viktorz08.lab1b.appservices.command.impls;

import com.hotmail.viktorz08.lab1b.repository.EmployerDataRepository;
import com.hotmail.viktorz08.lab1b.repository.entities.Employers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Collections;
import java.util.Random;
import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("/application-context2.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateRandomRowsCommandTest2 {

    @Autowired
    CreateRandomRowsCommand command;

    @Autowired
    EmployerDataRepository repository;

    private EntityManager manager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.manager = entityManager;
    }

    @Before
    @Transactional
    public void setUp() throws IOException {
        executeScript("scripts/create_table.sql");
    }

    @Test
    @Transactional
    public void testOutputtingEntitiesWhenThereAreNoEntities() {
        assertEquals(Collections.<Employers>emptyList(), repository.getEmployersData());
    }

    @Test
    @Transactional
    public void testExecuteZero() throws Exception {
        command.setRandom(new Random(0));
        command.setRandomRowCount(0);
        command.execute();

        assertEquals(0,repository.getEmployersData().size());
    }

    @Test
    @Transactional
    public void testExecuteHundred() throws Exception {
        command.setRandom(new Random(0));
        command.setRandomRowCount(100);
        command.execute();

        assertEquals(100,repository.getEmployersData().size());
    }

    @Test
    @Transactional
    public void testExecute() throws Exception {
        command.setRandom(new Random(0));
        command.setRandomRowCount(2);
        command.execute();

        assertEquals(2,repository.getEmployersData().size());
    }

    private void executeScript(String fileName) throws IOException {
        Resource script = new ClassPathResource(fileName);

        LineNumberReader scriptReader = new LineNumberReader(new BufferedReader(new FileReader(script.getFile())));

        String commentPrefix = "--";
        String separator = ";";
        String queries = ScriptUtils.readScript(scriptReader, commentPrefix, separator);

        StringTokenizer queryTokenizer = new StringTokenizer(queries, ";");
        while (queryTokenizer.hasMoreTokens()) {
            String queryStr = queryTokenizer.nextToken();
            Query query = manager.createNativeQuery(queryStr);
            query.executeUpdate();
        }
    }
}