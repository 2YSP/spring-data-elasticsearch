package cn.sp.controller;

import cn.sp.domain.Person;
import cn.sp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 2YSP on 2018/4/29.
 */
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PutMapping("/api/person")
    public Person savePerson(@RequestBody Person person){
        return  personService.save(person);
    }

}
