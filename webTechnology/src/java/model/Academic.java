/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author jms
 */
@Entity
public class Academic {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    
	    @Column(name = "academic_id")
	    private int id;
	    
	    @Column(name = "academic_name")
	    private String name;
	
	    @Enumerated(EnumType.STRING)
	    @Column(name = "type")
	    private AcademicType type;

    public Academic() {
    }

    public Academic(int id, String name, AcademicType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AcademicType getType() {
        return type;
    }

    public void setType(AcademicType type) {
        this.type = type;
    }

  
}
