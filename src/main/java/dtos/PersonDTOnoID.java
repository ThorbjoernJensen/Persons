package dtos;

public class PersonDTOnoID {
//   format {"fName":"Kurt","lName":"Wonnegut", phone:"12345678"}
// for creating objects from outside


    private String fName;
    private String lName;
    private String phone;

    public PersonDTOnoID(String fName, String lName, String phone) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
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
}
