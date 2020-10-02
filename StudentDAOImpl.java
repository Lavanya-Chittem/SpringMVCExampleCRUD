package com.mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.mvc.model.Student;

public class StudentDAOImpl implements StudentDAO {
	private JdbcTemplate jdbcTemplate;
	
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void saveOrUpdate(Student student) {
		if (student.getId() > 0) {
	        // update
	        String sql = "UPDATE student SET name=?, email=?, phone=?, "
	                    + "password=? WHERE id=?";
	        jdbcTemplate.update(sql, student.getName(), student.getEmail(),
	                student.getPhone(), student.getPassword(), student.getId());
	    } else {
	        // insert
	        String sql = "INSERT INTO student (name, email, phone, password)"
	                    + " VALUES (?, ?, ?, ?)";
	        jdbcTemplate.update(sql, student.getName(), student.getEmail(),
	                student.getPhone()  ,student.getPassword());
	    }
	}

	@Override
	public void delete(int id) {
		
		String sql = "Delete from student where id =?";
		jdbcTemplate.update(sql , id);
		
	}

	@Override
	public Student get(int id) {
		 String sql = "SELECT * FROM student WHERE id=" + id;
		    return jdbcTemplate.query(sql, new ResultSetExtractor<Student>() {
		 
		        @Override
		        public Student extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		            if (rs.next()) {
		                Student student = new Student();
		                student.setId(rs.getInt("id"));
		                student.setName(rs.getString("name"));
		                student.setEmail(rs.getString("email"));
		                student.setPhone(rs.getString("phone"));
		                student.setPassword(rs.getString("password"));
		                return student;
		            }
		 
		            return null;
		        }
		 
		    });
	}

	@Override
	public List<Student> list() {
		String sql = "SELECT * FROM Student";
	     
	    return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Student.class));  
	}

}
