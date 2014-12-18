package com.hotmail.viktorz08.lab1b.appservices.query.impls;

import com.hotmail.viktorz08.lab1b.appservices.command.impls.CreateRandomRowsCommand;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration("/application-context2.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class GetDuplicatesNameCommandTest2 {

    @Autowired
    GetDuplicatesNameCommand command;

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
    public void testExecuteGet() throws Exception {
        List<Employers> result = command.execute();
        assertEquals(0, result.size());
    }

    @Test
    @Transactional
    public void testExecuteWithCheck() throws Exception {
        executeScript("scripts/insert_without_same_names.sql");

        List<Employers> result = command.execute();

        assertEquals(0, result.size());
    }

    @Test
    @Transactional
    public void testExecuteWithCheck2() throws Exception {
        executeScript("scripts/insert_needed_entities.sql");

        List<Employers> result = command.execute();

        assertEquals(1, result.size());
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