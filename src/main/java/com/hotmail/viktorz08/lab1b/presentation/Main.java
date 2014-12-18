package com.hotmail.viktorz08.lab1b.presentation;

import com.hotmail.viktorz08.lab1b.appservices.command.Command;
import com.hotmail.viktorz08.lab1b.appservices.command.CommandExecutor;
import com.hotmail.viktorz08.lab1b.appservices.command.impls.CreateRandomRowsCommand;
import com.hotmail.viktorz08.lab1b.appservices.query.QueryCommand;
import com.hotmail.viktorz08.lab1b.appservices.query.QueryExecutor;
import com.hotmail.viktorz08.lab1b.repository.entities.Employers;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

/**
 * Created by Victor on 10/30/2014.
 */
@Configuration
@EnableTransactionManagement
@ImportResource("application-context.xml")
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        CommandExecutor executor = null;
        executor = (CommandExecutor) context.getBean("CreateRandomRowsInvoker");
        Command command = (Command) context.getBean("CreateRandomRowsCommand");
        ((CreateRandomRowsCommand) command).setRandomRowCount(10);

        QueryExecutor executor2;
        executor2 = (QueryExecutor) context.getBean("getDuplicatesNameDataInvoker");
        QueryCommand<List<Employers>> command2 = (QueryCommand<List<Employers>>) context.getBean("getDuplicatesNameDataCommand");

//        executor.invoke(command);
        List<Employers> list = (List<Employers>) executor2.invoke(command2);

        list.forEach( employer ->{
                    System.out.println(employer.toString());
                }
        );
    }
}
