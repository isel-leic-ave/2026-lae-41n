package pt.isel;

import pt.isel.lae41n.Mapper;
import pt.isel.lae41n.domain.Person;
import pt.isel.lae41n.domain.PersonDto;


public class PersonDto2PersonBaseline implements Mapper<PersonDto, Person> {
    @Override
    public Person mapFrom(PersonDto src) {
        return new Person(src.getName(), src.getFrom());
    }
}
