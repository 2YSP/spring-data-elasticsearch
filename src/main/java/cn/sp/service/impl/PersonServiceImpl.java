package cn.sp.service.impl;

import cn.sp.domain.Person;
import cn.sp.repository.PersonRepository;
import cn.sp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 2YSP on 2018/4/29.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Override
    public Person save(Person p) {

        Person person = personRepository.save(p);
        return person;
    }
}
