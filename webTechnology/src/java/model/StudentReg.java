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
public class StudentReg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regId;
    private String regDate;
    

	@ManyToOne
	@JoinColumn(name = "studentId")
	private Student student;

	@ManyToOne
        @JoinColumn(name = "semester_id")
        private Semester semester;

    public StudentReg() {
    }

    public StudentReg(int regId, String regDate, Student student, Semester semester) {
        this.regId = regId;
        this.regDate = regDate;
        this.student = student;
        this.semester = semester;
    }

    public int getRegId() {
        return regId;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
	
	
}
