package dtos;

import entities.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDTO {
//    {"fName":"Kurt","lName":"Wonnegut", phone:"12345678","id":0}

    private int id;
    private String fName;
    private String lName;
    private String phone;

    public PersonDTO(int id, String fName, String lName, String phone) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.fName = person.getFirstName();
        this.lName = person.getLastName();
        this.phone = person.getPhone();
    }


    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPhone() {
        return phone;
    }

    public static List<PersonDTO> getDTOList(List<Person> personList) {
        List<PersonDTO> personDTOList = new ArrayList<>();
        personList.forEach(person -> personDTOList.add(new PersonDTO(person)));
        return personDTOList;
    }
}
