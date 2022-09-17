package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Person;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonFacade implements IPersonFacade {
    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade facade = getInstance(emf);

        facade.addPerson("Peter", "Snietchel", "506043");
        facade.populator();
    }

    private PersonFacade() {

    }

    public static PersonFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void populator() {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade facade = PersonFacade.getInstance(emf);
        facade.addPerson("Lars", "Svendsen", "80988093");
        facade.addPerson("Per", "Prop", "9540");
        facade.addPerson("Ole", "Sko", "654321");
//        em.getTransaction().begin();
//        Person p1 = new Person("Lars", "Svendsen", "80988093");
//        Person p2 = new Person("Per", "Prop", "9540");
//        Person p3 = new Person("Ole", "Sko", "654321");
//        em.persist(p1);
//        em.persist(p2);
//        em.persist(p3);
//        em.getTransaction().commit();
//        em.close();
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
        Person p1 = em.find(Person.class, id);
        PersonDTO personDTO = new PersonDTO(p1);
        return personDTO;
    }

    @Override
    public PersonsDTO getAllPersons() {
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<PersonDTO> personDTOList = PersonDTO.getDTOList(query.getResultList());
        PersonsDTO personsDTO = new PersonsDTO(personDTOList);
        return personsDTO;
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) {
        return null;
    }

    public long getPersonCount() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT COUNT(p) FROM Person p");
        return (long) query.getSingleResult();
    }
}
