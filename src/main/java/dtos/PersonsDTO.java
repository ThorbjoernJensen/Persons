package dtos;

import java.util.List;

public class PersonsDTO {

    //    format
    //    {
//        "all" :[
//        {"fName":"Kurt","lName":"Wonnegut","phone":"12345678","id":0},
//        {"fName":"Peter","lName":"Hansen","phone":"12345678","id":1}
//  ]
//    }

    List<PersonDTO> all;

    public PersonsDTO(List<PersonDTO> all) {
        this.all = all;
    }

}
