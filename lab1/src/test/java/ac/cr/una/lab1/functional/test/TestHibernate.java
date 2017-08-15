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
    public void testSave(){
        //Se empieza la transaccion
        session.beginTransaction();
        
        //se crean los objetos
        Person person = new Person();
        PassportDetail passportDetail = new PassportDetail();
        
        //Seteamos los datos del passport
        passportDetail.setPassportno("123456789");
        //Salvamos el passport y verificamos el ID en la base
        session.save(passportDetail);
        System.out.println("Passport ID: " + passportDetail.getId());
        
        
        //Seteamos los datos de la persona y el passport que acabamos de salvar
        person.setName("Sergio");
        person.setPassport_detail(passportDetail);
        
        //salvamos la persona
        session.save(person);
        
        //obtenemos el id para verificar si se cumple el test
        long idPerson = person.getId();
        
        //Probamos el TEST
        assertTrue(idPerson > 0);
   
        
        
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
