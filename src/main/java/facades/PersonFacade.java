package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Person;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PersonFacade implements IPersonFacade{
    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade facade = getFacadeInstance(emf);

        facade.addPerson("Peter", "Snietchel", "506043");
    }

    private PersonFacade() {}

    public static PersonFacade getFacadeInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }




    @Override
    public PersonDTO addPerson(String fName, String lName, String phone) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Person p1 = new Person(fName, lName, phone);
        em.persist(p1);
        em.getTransaction().commit();

        PersonDTO p1DTO = new PersonDTO(p1);
        em.close();
        return p1DTO;
    }

    @Override
    public PersonDTO deletePerson(int id) {
        return null;
    }

    @Override
    public PersonDTO getPerson(int id) {
        EntityManager em = getEntityManager();
        Person p1=em.find(Person.class, id);
        PersonDTO personDTO = new PersonDTO(p1);
        return personDTO;
    }

    @Override
    public PersonsDTO getAllPersons() {
        return null;
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) {
        return null;
    }
}
