package com.hotmail.viktorz08.lab1b.appservices.command.impls;

import com.hotmail.viktorz08.lab1b.appservices.command.Command;
import com.hotmail.viktorz08.lab1b.repository.EmployerDataRepository;
import com.hotmail.viktorz08.lab1b.repository.entities.Employers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Random;

/**
 * Created by Victor on 12/3/2014.
 */

@Service(value = "CreateRandomRowsCommand")
public class CreateRandomRowsCommand implements Command {
    private int randomRowCount = 0;
    private Random random;

    @Autowired
    protected EmployerDataRepository repository;

    public CreateRandomRowsCommand() {
        this(0,new Random());
    }

    public CreateRandomRowsCommand(int randomRowCount, Random random) {
        this.randomRowCount = randomRowCount;
        this.random = random;
    }

    public void setRandomRowCount(int randomRowCount) {
        this.randomRowCount = randomRowCount;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    @Override
    public void execute() {
        int count = randomRowCount;

        for (int i = 0; i < count; i++) {
            Employers newData = new Employers();
            newData.setName("Name" + (random.nextInt(10)));
            newData.setSurname("Surname" + (random.nextInt(10)));
            String randDate = "1995-03-0" + ((random.nextInt(9) )+1 );
            newData.setBirthday(Date.valueOf(randDate));
            newData.setSalary(((double) (random.nextInt(10000))) / 100 + 1000);

            repository.addEmployerData(newData);
        }

    }
}
