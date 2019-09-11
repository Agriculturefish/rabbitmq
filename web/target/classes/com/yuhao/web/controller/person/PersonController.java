package com.yuhao.web.controller.person;

import com.yuhao.business.person.PersonService;
import com.yuhao.dao.domain.model.TPerson;
import com.yuhao.dao.domain.vo.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedHashSet;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/index")
    @Transactional
    public String save(Model model){
        Location location1 = new Location("深圳","2.5");
        Collection<Location> locations = new LinkedHashSet<>();
        locations.add(location1);
        TPerson person = new TPerson("dennis",24);
        person.setLocations(locations);
        personService.save(person);
        model.addAttribute("name",personService.findPerson("dennis").getName());
        return "user/user";
    }

}
