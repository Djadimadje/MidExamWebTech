/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author jms
 */
public class CourseDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
	@OneToOne
	@JoinColumn(name = "courseId")
	private Course courseId;
	
	private String name;
	private String description;

    public CourseDefinition() {
    }

    public CourseDefinition(int id, Course courseId, String name, String description) {
        this.id = id;
        this.courseId = courseId;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
        
        
}
