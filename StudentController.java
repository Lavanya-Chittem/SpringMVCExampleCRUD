package com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.dao.StudentDAO;
import com.mvc.model.Student;

@Controller
public class StudentController {
	
	@Autowired
	private StudentDAO studentDAO;

	@RequestMapping(value="/")
	public ModelAndView listStudent(ModelAndView model) throws IOException{
	    List<Student> listStudent = studentDAO.list();
	    model.addObject("listStudent", listStudent);
	    model.setViewName("StudentList");
	 
	    return model;
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newStudent(ModelAndView model) {
	    Student student = new Student();
	    model.addObject("student", student);
	    model.setViewName("StudentForm");
	    return model;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView saveStudent(@ModelAttribute Student student) {
	    studentDAO.saveOrUpdate(student);
	    return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateStudent(@ModelAttribute Student student) {
	    studentDAO.saveOrUpdate(student);
	    return new ModelAndView("redirect:/");
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
	    studentDAO.delete(id);
	    return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView showEdit(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Student student= studentDAO.get(id);
		ModelAndView model = new ModelAndView("StudentForm");
	    model.addObject("student", student);
	    return model;
	}
}
