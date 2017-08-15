/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.una.lab1.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name="person")
public class Person {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @Column(name="name")
    private String name;
    
    @JoinColumn(name="passport_detail_id")
    @OneToOne
    private PassportDetail passport_detail;
//    `id` bigint(20) NOT NULL AUTO_INCREMENT,
//`name` varchar(255) DEFAULT NULL,
//`passport_detail_id` bigint(20) DEFAULT NULL,
    public Person() {
    }

    public Person(long id, String name, PassportDetail passport_detail) {
        this.id = id;
        this.name = name;
        this.passport_detail = passport_detail;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PassportDetail getPassport_detail() {
        return passport_detail;
    }

    public void setPassport_detail(PassportDetail passport_detail) {
        this.passport_detail = passport_detail;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", passport_detail=" + passport_detail + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.passport_detail);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.passport_detail, other.passport_detail)) {
            return false;
        }
        return true;
    }

    
    
}
