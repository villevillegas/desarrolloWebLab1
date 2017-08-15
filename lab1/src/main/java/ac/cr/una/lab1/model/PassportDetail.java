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
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name="passport_detail")
public class PassportDetail {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    
    @Column(name="passportno")
    private String passportno;

    public PassportDetail(Long id, String passportno) {
        this.id = id;
        this.passportno = passportno;
    }

    public PassportDetail() {
    }
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassportno() {
        return passportno;
    }

    public void setPassportno(String passportno) {
        this.passportno = passportno;
    }

    @Override
    public String toString() {
        return "Passport_detail{" + "id=" + id + ", passportno=" + passportno + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.passportno);
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
        final PassportDetail other = (PassportDetail) obj;
        if (!Objects.equals(this.passportno, other.passportno)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
