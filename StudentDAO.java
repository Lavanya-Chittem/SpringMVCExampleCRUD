package com.mvc.dao;

import java.util.List;

import com.mvc.model.Student;

public interface StudentDAO {

	public void saveOrUpdate(Student contact);
    
    public void delete(int id);
     
    public Student get(int id);
     
    public List<Student> list();
}
