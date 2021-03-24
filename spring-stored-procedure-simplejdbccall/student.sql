CREATE TABLE `student` (
  `student_id` int unsigned COLLATE utf8mb4_unicode_ci NOT NULL AUTO_INCREMENT,
  `student_name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_dob` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_email` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_address` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

insert  into `student`(`student_id`,`student_name`,`student_dob`,`student_email`,`student_address`) values (1,'Sumit','01-01-1980','sumit@email.com','Garifa'),
(2,'Gourab','01-01-1982','gourab@email.com','Garia'),
(3,'Debina','01-01-1982','debina@email.com','Salt Lake'),
(4,'Souvik','01-01-1992','souvik@email.com','Alipore'),
(5,'Liton','01-01-1990','liton@email.com','Salt Lake');

DELIMITER $$
CREATE
    PROCEDURE `roytuts`.`get_student`(IN in_student_id INTEGER,
	    OUT out_student_name VARCHAR(30),
	    OUT out_student_dob VARCHAR(10),
	    OUT out_student_email VARCHAR(80),
	    OUT out_student_address VARCHAR(255))
    BEGIN
	SELECT student_name, student_dob, student_email, student_address
	INTO out_student_name, out_student_dob, out_student_email, out_student_address
	FROM student WHERE student_id = in_student_id;
    END$$
DELIMITER ;

DELIMITER $$
CREATE
    PROCEDURE `roytuts`.`get_all_students`()
    BEGIN
	SELECT * FROM student;
    END$$
DELIMITER ;