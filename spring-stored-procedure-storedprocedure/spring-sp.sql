CREATE DATABASE IF NOT EXISTS `roytuts`;
USE `roytuts`;

-- Dumping structure for procedure roytuts.get_all_students
DELIMITER //
CREATE PROCEDURE `get_all_students`()
BEGIN
	SELECT * FROM student;
END//
DELIMITER ;

-- Dumping structure for procedure roytuts.get_student
DELIMITER //
CREATE PROCEDURE `get_student`(
	IN `in_student_id` INT,
	OUT `out_student_name` VARCHAR(30),
	OUT `out_student_dob` VARCHAR(10),
	OUT `out_student_email` VARCHAR(80),
	OUT `out_student_address` VARCHAR(255)
)
BEGIN
	SELECT student_name, student_dob, student_email, student_address
	INTO out_student_name, out_student_dob, out_student_email, out_student_address
	FROM student WHERE student_id = in_student_id;
END//
DELIMITER ;

-- Dumping structure for table roytuts.student
CREATE TABLE IF NOT EXISTS `student` (
  `student_id` int unsigned NOT NULL AUTO_INCREMENT,
  `student_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_dob` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_email` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_address` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table roytuts.student: ~6 rows (approximately)
INSERT INTO `student` (`student_id`, `student_name`, `student_dob`, `student_email`, `student_address`) VALUES
	(1, 'Sumit', '01-01-1980', 'sumit@email.com', 'Garifa'),
	(2, 'Sumit', '01-01-1980', 'sumit@email.com', 'Garifa'),
	(3, 'Anjisnu', '01-01-1982', 'gourab@email.com', 'Garia'),
	(4, 'Debina', '01-01-1982', 'debina@email.com', 'Salt Lake'),
	(5, 'Souvik', '01-01-1992', 'souvik@email.com', 'Alipore'),
	(6, 'Liton', '01-01-1990', 'liton@email.com', 'Salt Lake');
