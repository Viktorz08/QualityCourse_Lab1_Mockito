package com.hotmail.viktorz08.lab1b.appservices.command.impls;

import com.hotmail.viktorz08.lab1b.appservices.command.Command;
import com.hotmail.viktorz08.lab1b.appservices.command.CommandExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Victor on 12/3/2014.
 */
@Service(value = "CreateRandomRowsInvoker")
public class CreateRandomRowsInvoker implements CommandExecutor {
    @Override
    @Transactional
    public void invoke(Command cmd) {
        cmd.execute();
    }
}
