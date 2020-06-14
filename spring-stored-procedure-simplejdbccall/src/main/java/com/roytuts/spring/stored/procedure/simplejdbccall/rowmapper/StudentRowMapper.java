package com.roytuts.spring.stored.procedure.simplejdbccall.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.roytuts.spring.stored.procedure.simplejdbccall.model.Student;

public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student();
		student.setStudentId(rs.getInt("student_id"));
		student.setStudentName(rs.getString("student_name"));
		student.setStudentDob(rs.getString("student_dob"));
		student.setStudentEmail(rs.getString("student_email"));
		student.setStudentAddress(rs.getString("student_address"));
		return student;
	}

}
