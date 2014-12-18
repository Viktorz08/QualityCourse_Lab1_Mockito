package com.hotmail.viktorz08.lab1b.appservices.query.impls;

import com.hotmail.viktorz08.lab1b.appservices.query.QueryCommand;
import com.hotmail.viktorz08.lab1b.repository.EmployerDataRepository;
import com.hotmail.viktorz08.lab1b.repository.entities.Employers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Victor on 12/3/2014.
 */
@Service(value = "getDuplicatesNameDataCommand")
public class GetDuplicatesNameCommand implements QueryCommand<List<Employers>> {

    @Autowired
    protected EmployerDataRepository repository;

    @Override
    public List<Employers> execute() {
        List<Employers> dataList = repository.getEmployersData();

        Map<String, Integer> nameMap = new HashMap<>();

        dataList.forEach(data -> {
            String name = data.getName();
            nameMap.putIfAbsent(name, 0);
            nameMap.put(name, nameMap.get(name) + 1);
        });

        List<Employers> duplicates = new ArrayList<>();
        dataList.forEach(data -> {
            String name = data.getName();
            int count = nameMap.get(name);
            if(count != 1){
                duplicates.add(data);
                nameMap.put(name,count-1);
            }
        });

        return duplicates;
    }
}
