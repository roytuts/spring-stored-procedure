package com.roytuts.spring.stored.procedure.storedprocedure.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

import com.roytuts.spring.stored.procedure.storedprocedure.model.Student;
import com.roytuts.spring.stored.procedure.storedprocedure.rowmapper.StudentRowMapper;

@Repository
public class StudentDao {

	@Autowired
	private DataSource dataSource;

	private final String DB_NAME = "roytuts";
	private final String PROC_GET_STUDENT = "get_student";
	private final String PROC_GET_ALL_STUDENTS = "get_all_students";

	public Student getStudentById(int studentId) {
		StudentProcedure studentProcedure = new StudentProcedure();
		Student student = studentProcedure.execute(studentId);
		return student;
	}

	private class StudentProcedure extends StoredProcedure {
		public StudentProcedure() {
			super(dataSource, DB_NAME + "." + PROC_GET_STUDENT);
			declareParameter(new SqlParameter("in_student_id", Types.INTEGER));
			declareParameter(new SqlOutParameter("out_student_name", Types.VARCHAR));
			declareParameter(new SqlOutParameter("out_student_dob", Types.VARCHAR));
			declareParameter(new SqlOutParameter("out_student_email", Types.VARCHAR));
			declareParameter(new SqlOutParameter("out_student_address", Types.VARCHAR));
			compile();
		}

		public Student execute(int studentId) {
			Map<String, Object> result = super.execute(studentId);
			// get Student object
			Student student = new Student();
			// simple the studentId
			student.setStudentId(studentId);
			// set Name, must be same as out param in procedure
			student.setStudentName((String) result.get("out_student_name"));
			// set Date of Birth, must be same as out param in procedure
			student.setStudentDob((String) result.get("out_student_dob"));
			// set Email, must be same as out param in procedure
			student.setStudentEmail((String) result.get("out_student_email"));
			// set Address, must be same as out param in procedure
			student.setStudentAddress((String) result.get("out_student_address"));
			return student;
		}
	}

	public List<Student> getAllStudents() {
		StudentProcedure2 procedure2 = new StudentProcedure2();
		Map<String, Object> map = procedure2.execute();
		@SuppressWarnings("unchecked")
		List<Student> students = (List<Student>) map.get("students");
		return students;
	}

	private class StudentProcedure2 extends StoredProcedure {
		public StudentProcedure2() {
			super(dataSource, DB_NAME + "." + PROC_GET_ALL_STUDENTS);
			declareParameter(new SqlReturnResultSet("students", new StudentRowMapper()));
			compile();
		}

		public Map<String, Object> execute() {
			// no IN parameter so passing empty HashMap
			Map<String, Object> results = super.execute(new HashMap<>());
			return results;
		}
	}

}
