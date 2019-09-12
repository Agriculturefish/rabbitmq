package com.yuhao.business.person;

import com.yuhao.dao.domain.model.TPerson;
import com.yuhao.dao.domain.vo.PersonVo;
import com.yuhao.dao.repository.IPersonRepository;
import com.yuhao.dao.repository.PersonNativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    IPersonRepository personRepository;

    @Autowired
    PersonNativeRepository personNativeRepository;

    public void save(TPerson person){
        personRepository.save(person);
    }

    public TPerson findPerson(String name){
        List<TPerson> personList = personRepository.findAllByName(name);
        if(personList!=null)
            return personList.get(0);
        return  personRepository.findByName(name);
    }

    public void setName(String name){
        personNativeRepository.setString("name",name);
    }

    public String getName(String key){
        return personNativeRepository.getString(key);
    }

    public void setPerson(PersonVo person){
        personNativeRepository.setObject(person.getName(),person);
    }

    public PersonVo getPerson(String key){
        return personNativeRepository.getObject(key);
    }

}
