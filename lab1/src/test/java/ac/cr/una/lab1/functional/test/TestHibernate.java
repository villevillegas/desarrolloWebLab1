package ac.cr.una.lab1.functional.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.hibernate.Session;
import ac.cr.una.lab1.HibernateUtil;
import ac.cr.una.lab1.NewHibernateUtil;
import ac.cr.una.lab1.model.PassportDetail;
import ac.cr.una.lab1.model.Person;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.query.Query;


import org.junit.AfterClass;
import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Sergio
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestHibernate {
    private static Session session;

    
    public TestHibernate() {
    }
    

    @AfterClass
    public static void tearDown() {
        session.close();
        //HibernateUtil.shutdown();
        NewHibernateUtil.shutdown();
    }
    
    @Before
    public void setUp() {
        
        //session = HibernateUtil.getSessionFactory().openSession();
        session = NewHibernateUtil.getSessionFactory().openSession();
        
    }
    
    @Test
    public void testDefault(){
        assertTrue(1>0);
        
    }
    @Test
    public void test1Save(){
        //El nombre con el numero 1 es para que se respete el @FixMethodOrder(MethodSorters.NAME_ASCENDING)
        //Se empieza la transaccion
        session.beginTransaction();
        
        //se crean los objetos
        
        Person person = new Person();
        Person person2 = new Person();
        Person person3 = new Person();
        Person person4 = new Person();
        PassportDetail passportDetail = new PassportDetail();
        PassportDetail passportDetail2 = new PassportDetail();
        PassportDetail passportDetail3 = new PassportDetail();
        PassportDetail passportDetail4 = new PassportDetail();
        
        //Seteamos los datos del passport
        passportDetail.setPassportno("123456789");
        passportDetail2.setPassportno("901234567");
        passportDetail3.setPassportno("890123456");
        passportDetail4.setPassportno("789012345");
        //Salvamos el passport y verificamos el ID en la base
        session.save(passportDetail);
        session.save(passportDetail2);
        session.save(passportDetail3);
        session.save(passportDetail4);
        //System.out.println("Passport ID: " + passportDetail.getId());
        
        
        //Seteamos los datos de la persona y el passport que acabamos de salvar
        person.setName("Sergio");
        person.setPassport_detail(passportDetail);
        person2.setName("Susana");
        person2.setPassport_detail(passportDetail2);
        person3.setName("Mike");
        person3.setPassport_detail(passportDetail3);
        person4.setName("Medford");
        person4.setPassport_detail(passportDetail4);
        
        //salvamos la persona
        session.save(person);
        session.save(person2);
        session.save(person3);
        session.save(person4);
        
        //obtenemos el id para verificar si se cumple el test
        long idPerson = person.getId();
        long idPerson2 = person2.getId();
        long idPerson3 = person3.getId();
        long idPerson4 = person4.getId();
        
        // commit transaction
        session.getTransaction().commit();
        
        //Probamos el TEST
        assertTrue(idPerson > 0);
        assertTrue(idPerson2 > 0);
        assertTrue(idPerson3 > 0);
        assertTrue(idPerson4 > 0);
        
        
        
    }
   @Test
    public void test2List(){
        //El nombre con el numero 2 es para que se respete el @FixMethodOrder(MethodSorters.NAME_ASCENDING)
        ArrayList<Person> Personlist;
        ArrayList<PassportDetail> passportDetails;
  
        String tableName = "passport_detail";
        String personTable = "person";
        
        //Se hace un select de los pasaportes.
        Query query = session.createNativeQuery("SELECT * FROM " + tableName + ";", PassportDetail.class);
        passportDetails = (ArrayList<PassportDetail>) query.getResultList();
        //se imprime la lista de los pasaportes.
        System.out.println("RESULT LIST PASSPORT"); 
        System.out.println(passportDetails.toString());
        
        //Se hace un select a la tabla Person
        Query queryPerson = session.createNativeQuery("SELECT * FROM " + personTable + ";", Person.class);
        
        //Se guarta los resultados en el arraylist
        Personlist = (ArrayList < Person >)queryPerson.getResultList();
        //Verificamos que el personList no sea nulo
        assertNotNull(Personlist);
        //Se imprimen las personas. Esto tambien imprime sus respettivos pasaportes
        System.out.println(Personlist.toString());
        //Se verifican que tenga sus respectivos pasaportes
        for(Iterator i = Personlist.iterator(); i.hasNext();){
            Person person = (Person) i.next();
            //se verifica que el respectivo pasaporte no venga nulo
            //Se imprime el objeto pasaporte del usuario en cuestion
            assertNotNull(person.getPassport_detail());
            System.out.println(person.getPassport_detail().toString());
        }

    }

}
//http://what-when-how.com/hibernate/advanced-entity-association-mappings-hibernate/
//https://www.tutorialspoint.com/hibernate/hibernate_list_mapping.htm