package com.yuhao.business.person;

import com.yuhao.dao.domain.model.TPerson;
import com.yuhao.dao.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public void save(TPerson person){
        personRepository.save(person);
    }

    public TPerson findPerson(String name){
        List<TPerson> personList = personRepository.findAllByName(name);
        if(personList!=null)
            return personList.get(0);
        return  personRepository.findByName(name);
    }


}
