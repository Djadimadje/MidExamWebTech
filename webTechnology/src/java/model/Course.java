/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author jms
 */
@Entity
public class Course {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String course_code;

    private String course_name;

    @ManyToOne
    @JoinColumn(name = "semesterId")
    private Semester semester;   

    public Course() {
    }

    public Course(int id, String course_code, String course_name, Semester semester) {
        this.id = id;
        this.course_code = course_code;
        this.course_name = course_name;
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

   
}
