CREATE TABLE `admin`  (
  `admin_name` varchar(30) NOT NULL,
  `salt_password` varchar(200) NOT NULL,
  `role` char(10) NOT NULL,
  PRIMARY KEY (`admin_name`)
);

CREATE TABLE `course`  (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(30) NOT NULL,
  PRIMARY KEY (`course_id`)
);

CREATE TABLE `course_student`  (
  `student_id` varchar(30) NOT NULL,
  `course_id` int NOT NULL
);

CREATE TABLE `paper`  (
  `paper_id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `student_id` varchar(30) NOT NULL,
  `create_time` datetime NOT NULL,
  `validate_time` datetime NOT NULL,
  `score` int ZEROFILL NOT NULL DEFAULT 0,
  PRIMARY KEY (`paper_id`)
);

CREATE TABLE `paper_question`  (
  `question_id` bigint NOT NULL,
  `paper_id` int NOT NULL
);

CREATE TABLE `question`  (
  `question_id` bigint NOT NULL,
  `course_id` int NOT NULL,
  `content` varchar(300) NOT NULL,
  `right_answer` varchar(50) NOT NULL,
  `error1` varchar(50) NOT NULL,
  `error2` varchar(50) NOT NULL,
  `error3` varchar(50) NOT NULL,
  `hard` int NOT NULL,
  PRIMARY KEY (`question_id`)
);

CREATE TABLE `student`  (
  `student_id` varchar(30) NOT NULL,
  `student_name` varchar(30) NOT NULL,
  `salt_password` varchar(150) NOT NULL
);

CREATE TABLE `table_1`  ();

ALTER TABLE `course_student` ADD CONSTRAINT `fk_student_course_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `course_student` ADD CONSTRAINT `fk_course_course_student` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `paper` ADD CONSTRAINT `fk_paper_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `paper` ADD CONSTRAINT `fk_paper_studentt` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `paper_question` ADD CONSTRAINT `fk_paper_paper_question` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`paper_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `paper_question` ADD CONSTRAINT `fk_question_question_paper` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `question` ADD CONSTRAINT `fk_course_question` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE;

