package com.roytuts.spring.stored.procedure.simplejdbccall.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.roytuts.spring.stored.procedure.simplejdbccall.model.Student;
import com.roytuts.spring.stored.procedure.simplejdbccall.rowmapper.StudentRowMapper;

@Repository
public class StudentDao {

	@Autowired
	@Qualifier("simpleJdbcCall1")
	private SimpleJdbcCall simpleJdbcCall1;

	@Autowired
	@Qualifier("simpleJdbcCall2")
	private SimpleJdbcCall simpleJdbcCall2;

	private final String DB_NAME = "roytuts";
	private final String PROC_GET_STUDENT = "get_student";
	private final String PROC_GET_ALL_STUDENTS = "get_all_students";

	public Student getStudentUsingProcMethod1(int studentId) {
		// schema name
		simpleJdbcCall1.withCatalogName(DB_NAME);
		// procedure name
		simpleJdbcCall1.withProcedureName(PROC_GET_STUDENT);
		// in parameter, must be same as in the procedure
		// Map with key/value pair also be passed to addValue() function
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("in_student_id", studentId);
		// get result in key/value pair
		Map<String, Object> result = simpleJdbcCall1.execute(sqlParameterSource);
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

	public Student getStudentUsingProcMethod2(int studentId) {
		// schema name
		simpleJdbcCall2.withCatalogName(DB_NAME);
		// procedure name
		simpleJdbcCall2.withProcedureName(PROC_GET_STUDENT);
		// You can opt to declare one, some, or all the parameters explicitly
		simpleJdbcCall2.withoutProcedureColumnMetaDataAccess();
		// in parameter, must be same as in the procedure
		simpleJdbcCall2.useInParameterNames("in_student_id");
		// in and out parameters, must be same as in the procedure
		simpleJdbcCall2.declareParameters(new SqlParameter("in_student_id", Types.INTEGER),
				new SqlOutParameter("out_student_name", Types.VARCHAR),
				new SqlOutParameter("out_student_dob", Types.VARCHAR),
				new SqlOutParameter("out_student_email", Types.VARCHAR),
				new SqlOutParameter("out_student_address", Types.VARCHAR));
		// put in parameter value in Map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("in_student_id", studentId);
		// execute the procedure
		// get result in key/value pair
		Map<String, Object> result = simpleJdbcCall2.execute(map);
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

	public List<Student> getAllStudentUsingProcMethod1() {
		// schema name
		simpleJdbcCall1.withCatalogName(DB_NAME);

		// procedure name, BeanPropertyRowMapper or RowMapper

		// simpleJdbcCall1.withProcedureName(PROC_GET_ALL_STUDENTS).returningResultSet("students",
		// BeanPropertyRowMapper.newInstance(Student.class));

		simpleJdbcCall1.withProcedureName(PROC_GET_ALL_STUDENTS).returningResultSet("students", new StudentRowMapper());

		// get results in key/value pair
		Map<String, Object> results = simpleJdbcCall1.execute();

		@SuppressWarnings("unchecked")
		List<Student> students = (List<Student>) results.get("students");

		return students;
	}

}
