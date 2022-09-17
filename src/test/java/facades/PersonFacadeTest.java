package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonFacadeTest {
    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private Person p1, p2;
    private PersonDTO p1DTO, p2DTO;

    @BeforeAll
    public static void setUpClass(){
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PersonFacade.getInstance(emf);
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            p1 = new Person("Lars", "Supertester","3932048");
            p2 = new Person("Mogens", "Betatester","3838");
            em.persist(p1);
            em.persist(p2);
            em.getTransaction().commit();
            PersonDTO p1DTO = new PersonDTO(p1);
            PersonDTO p2DTO = new PersonDTO(p2);
        } finally {
            em.close();
        }
    }



    @Test
    void addPerson() {
        Person pTest = new Person("Preben", "Lossekjær", "100");
        PersonDTO pTestDTO = facade.addPerson(pTest.getFirstName(), pTest.getLastName(), pTest.getPhone());
        long expected = 3;
        long actual = facade.getPersonCount();
        assertEquals(expected, actual);
        String expectedFName = "Preben";
        String actualFName = pTestDTO.getfName();
        assertEquals(expectedFName, actualFName);
    }

    @Test
    void getAllPersons() {
        PersonsDTO personsDTO = facade.getAllPersons();
        long expectedLenght = 2;
        long actualLenght = personsDTO.getAll().size();
        assertEquals(expectedLenght, actualLenght);
    }

    @Test
    void deletePerson() {

    }

    @Test
    void getPerson() {
    }


    @Test
    void editPerson() {
    }

    @Test
    void getPersonCount() {
    }
}