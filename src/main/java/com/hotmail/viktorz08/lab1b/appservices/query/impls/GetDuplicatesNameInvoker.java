package com.hotmail.viktorz08.lab1b.appservices.query.impls;

import com.hotmail.viktorz08.lab1b.appservices.query.QueryExecutor;
import com.hotmail.viktorz08.lab1b.repository.entities.Employers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* Created by Victor on 12/3/2014.
*/
@Service(value="getDuplicatesNameDataInvoker")
public class GetDuplicatesNameInvoker implements QueryExecutor<List<Employers>, GetDuplicatesNameCommand> {
    @Transactional
    @Override
    public List<Employers> invoke(GetDuplicatesNameCommand cmd){
        return cmd.execute();
    }
}
