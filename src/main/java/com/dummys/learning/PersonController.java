package com.dummys.learning;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/learning")
@CrossOrigin
public class PersonController
{
    private List<Person> people = new ArrayList<Person>();
    private int count = 0;

    @RequestMapping(value = "/newperson", method = RequestMethod.GET)
    public Person getNewPerson()
    {
        Person person = new Person();
        person.setId( count++ );
        person.setName( "New Person" );

        return person;
    }

    @RequestMapping(value = "/addperson", method = RequestMethod.POST)
    public List<Person> addPerson( @RequestBody Person person )
    {
        if( person != null && person.getId() > 0 && person.getName() != null )
        {
            people.add( person );
            count++;
        }
        return people;
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public Person getPerson( @RequestParam(value = "id") long id )
    {
        for( Person person : people )
        {
            if( person.getId() == id )
            {
                return person;
            }
        }
        return null;
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public Person getPersonByPath( @PathVariable(value = "id") long id )
    {
        for( Person person : people )
        {
            if( person.getId() == id )
            {
                return person;
            }
        }
        return null;
    }
}
