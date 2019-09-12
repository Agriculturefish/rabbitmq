package com.yuhao.web.controller.person;

import com.yuhao.business.person.PersonService;
import com.yuhao.dao.domain.vo.PersonVo;
import com.yuhao.dao.repository.PersonNativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class DataController {
    @Autowired
    private PersonService personService;

    @RequestMapping("/set/name")
    public void setName(@RequestParam String name){
        personService.setName(name);
    }

    @RequestMapping("/set/person")
    public void setPerson(PersonVo personVo ){
        personService.setPerson(personVo);
    }

    @RequestMapping("/get/name")
    public String getName(@RequestParam String key){
        return personService.getName(key);
    }

    @RequestMapping("/get/person")
    public PersonVo getPerson(@RequestParam String key){
        return personService.getPerson(key);
    }

}
