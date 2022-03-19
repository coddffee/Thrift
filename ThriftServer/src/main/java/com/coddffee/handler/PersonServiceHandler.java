package com.coddffee.handler;

import com.coddffee.entity.Person;
import com.coddffee.enums.Gender;
import com.coddffee.exception.PersonException;
import com.coddffee.service.PersonService;

public class PersonServiceHandler implements PersonService.Iface {
    private Person person = new Person();
    @Override
    public Person newPerson(int id, String name, Gender gender)
            throws org.apache.thrift.TException {
        person.id = id;
        person.name = name;
        person.gender = gender;
        return person;
    }
    @Override
    public int getId() throws org.apache.thrift.TException {
        return person.id;
    }
    @Override
    public String getName() throws org.apache.thrift.TException {
        return person.name;
    }
    @Override
    public Gender getGender() throws org.apache.thrift.TException {
        return person.gender;
    }
    @Override
    public void printPerson() throws PersonException,org.apache.thrift.TException {
        System.out.println(person.id + "," + person.name + "," + person.gender + ".");
    }
}
