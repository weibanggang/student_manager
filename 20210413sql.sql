/*
SQLyog Ultimate v8.82 
MySQL - 5.6.26 : Database - student_manager
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`student_manager` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `student_manager`;

/*Table structure for table `class_schedule_card` */

DROP TABLE IF EXISTS `class_schedule_card`;

CREATE TABLE `class_schedule_card` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` varchar(32) DEFAULT NULL COMMENT '所属课程id',
  `teacher_id` varchar(32) DEFAULT NULL COMMENT '任课老师id',
  `teacher_name` varchar(32) DEFAULT NULL COMMENT '任课老师名字',
  `course_type` varchar(32) DEFAULT NULL COMMENT '课程类型',
  `course_name` varchar(32) DEFAULT NULL COMMENT '课程名称',
  `start_time` varchar(32) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(32) DEFAULT NULL COMMENT '结束时间',
  `WEEK` varchar(32) DEFAULT NULL COMMENT '星期',
  `STATUS` varchar(32) DEFAULT NULL COMMENT '状态',
  `remarks` text COMMENT '备注',
  PRIMARY KEY (`UUID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `class_schedule_card` */

LOCK TABLES `class_schedule_card` WRITE;

insert  into `class_schedule_card`(`UUID`,`course_id`,`teacher_id`,`teacher_name`,`course_type`,`course_name`,`start_time`,`end_time`,`WEEK`,`STATUS`,`remarks`) values (1,'2','1617883891698','韦邦杠','文学类','外国专业技术','2021-04-10 06:04:03','2021-04-10 8:4:3','六','未上课','自动计算时间'),(3,'2','1617842546979','21321','文学类','外国专业技术','2021-04-20 06:06:06','2021-04-20 8:6:6','二','未上课','3213'),(4,'2','1617883891698','韦邦杠','文学类','外国专业技术','2021-04-12 23:06:19','2021-04-13 1:6:19','一','未上课','123');

UNLOCK TABLES;

/*Table structure for table `classa` */

DROP TABLE IF EXISTS `classa`;

CREATE TABLE `classa` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT,
  `class_type` varchar(32) DEFAULT NULL COMMENT '班级类别',
  `class_name` varchar(32) DEFAULT NULL COMMENT '班级名称',
  `teacher_id` varchar(32) DEFAULT NULL COMMENT '班主任id',
  `teacher_name` varchar(32) DEFAULT NULL COMMENT '班主任姓名',
  `phone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `start_class_time` varchar(32) DEFAULT NULL COMMENT '开班日期',
  `end_class_time` varchar(32) DEFAULT NULL COMMENT '结班日期',
  `expect_number_people` varchar(32) DEFAULT NULL COMMENT '预招人数',
  `actual_number_people` varchar(32) DEFAULT NULL COMMENT '实际人数',
  `plan_number` varchar(32) DEFAULT NULL COMMENT '计划课时',
  `already_number` varchar(32) DEFAULT NULL COMMENT '已上课时',
  `course_id` varchar(32) DEFAULT NULL COMMENT '课程id',
  `course_name` varchar(32) DEFAULT NULL COMMENT '课程名称',
  `remarks` text COMMENT '备注',
  PRIMARY KEY (`UUID`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `classa` */

LOCK TABLES `classa` WRITE;

insert  into `classa`(`UUID`,`class_type`,`class_name`,`teacher_id`,`teacher_name`,`phone`,`start_class_time`,`end_class_time`,`expect_number_people`,`actual_number_people`,`plan_number`,`already_number`,`course_id`,`course_name`,`remarks`) values (1,'文学类','2021-第期-ewqe','1617883891698','韦邦杠','2121','2021-04-10','2021-04-07','132','1000','122','22','2','ewqe','21321'),(2,'文学类','2021-第期-外国专业技术','1617883891698','韦邦杠','12321321321','2021-04-07','2021-04-10','1','1','1','1','2','外国专业技术','321312321312'),(3,'艺术类','2021-第期-323232','1617883891698','韦邦杠','21321321','2021-04-10','2021-04-08','123','123','123','1000','6','323232','21321321'),(7,'艺术类','2021-第期-323232','1617842546979','21321','21321321','2021-04-20','2021-04-09','1','1','1','1','6','323232','2');

UNLOCK TABLES;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT,
  `course_type` varchar(32) DEFAULT NULL COMMENT '课程类型',
  `course_name` varchar(32) DEFAULT NULL COMMENT '课程名称',
  `remarks` text COMMENT '备注',
  PRIMARY KEY (`UUID`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

LOCK TABLES `course` WRITE;

insert  into `course`(`UUID`,`course_type`,`course_name`,`remarks`) values (2,'文学类','外国专业技术','12321'),(6,'艺术类','323232','32432');

UNLOCK TABLES;

/*Table structure for table `satisfaction` */

DROP TABLE IF EXISTS `satisfaction`;

CREATE TABLE `satisfaction` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT,
  `TIME` varchar(32) DEFAULT NULL COMMENT '调查时间',
  `content` varchar(32) DEFAULT NULL COMMENT '调查内容',
  `investigator` varchar(32) DEFAULT NULL COMMENT '调查人',
  `respondents` varchar(32) DEFAULT NULL COMMENT '被调查人',
  `survey_satisfaction` varchar(32) DEFAULT NULL COMMENT '调查满意度',
  `remarks` text COMMENT '备注',
  PRIMARY KEY (`UUID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `satisfaction` */

LOCK TABLES `satisfaction` WRITE;

insert  into `satisfaction`(`UUID`,`TIME`,`content`,`investigator`,`respondents`,`survey_satisfaction`,`remarks`) values (1,'2021-04-13','12321','21321321','21321312','非常满意','321321'),(3,'2021-04-23','21321','321321','321321','不满意','321');

UNLOCK TABLES;

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `student_id` varchar(32) NOT NULL,
  `classs` varchar(32) DEFAULT NULL COMMENT '班级',
  `NAME` varchar(32) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `birthday` varchar(32) DEFAULT NULL COMMENT '出生日期',
  `address` varchar(32) DEFAULT NULL COMMENT '家庭住址',
  `PASSWORD` varchar(32) DEFAULT NULL COMMENT '密码',
  `parents_name` varchar(15) DEFAULT NULL COMMENT '家长姓名',
  `parents_number` varchar(11) DEFAULT NULL COMMENT '家长号码',
  `number` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `sentrancedate` varchar(32) DEFAULT NULL COMMENT '入学时间',
  `qq` varchar(32) DEFAULT NULL COMMENT '邮箱地址',
  `state` varchar(10) DEFAULT NULL COMMENT '状态',
  `admissions_teacher` varchar(32) DEFAULT NULL COMMENT '招生老师',
  `grade` varchar(32) DEFAULT NULL COMMENT '学员等级',
  `remarks` text COMMENT '备注',
  PRIMARY KEY (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `student` */

LOCK TABLES `student` WRITE;

insert  into `student`(`student_id`,`classs`,`NAME`,`sex`,`birthday`,`address`,`PASSWORD`,`parents_name`,`parents_number`,`number`,`sentrancedate`,`qq`,`state`,`admissions_teacher`,`grade`,`remarks`) values ('1617889690204','1','321','男','2021-04-07','21321',NULL,'21321321','21321','213212132','2021-04-21','','毕业','ewrewrew','VIP学员','321321'),('1617889992784','1','32423','男','2021-04-21','23432',NULL,'432','32432','432432','2021-04-22','4324','在读','2','临时听课','432432');

UNLOCK TABLES;

/*Table structure for table `student_check` */

DROP TABLE IF EXISTS `student_check`;

CREATE TABLE `student_check` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(32) DEFAULT NULL COMMENT '学生ID',
  `student_name` varchar(32) DEFAULT NULL COMMENT '学生姓名',
  `sourse_id` varchar(32) DEFAULT NULL COMMENT '课程表ID',
  `course_name_s_e_time` varchar(320) DEFAULT NULL COMMENT '课程名称+开始-结束时间',
  `STATUS` varchar(32) DEFAULT NULL COMMENT '考勤状态',
  `remarks` text COMMENT '备注',
  PRIMARY KEY (`UUID`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `student_check` */

LOCK TABLES `student_check` WRITE;

insert  into `student_check`(`UUID`,`student_id`,`student_name`,`sourse_id`,`course_name_s_e_time`,`STATUS`,`remarks`) values (1,'12321','321','3213','21312','321','1321'),(2,'213','21312','312','321','未签到',NULL),(3,'1617889992784','32423',NULL,'外国专业技术（2021-04-12 23:06:19 - 2021-04-13 1:6:19）','正常',NULL),(4,'1617889690204','321',NULL,'外国专业技术（2021-04-12 23:06:19 - 2021-04-13 1:6:19）','缺勤',NULL),(5,'1617889992784','32423',NULL,'外国专业技术（2021-04-12 23:06:19 - 2021-04-13 1:6:19）','正常',NULL),(6,'1617889690204','321',NULL,'外国专业技术（2021-04-12 23:06:19 - 2021-04-13 1:6:19）','缺勤',NULL),(7,'1617889992784','32423',NULL,'外国专业技术（2021-04-12 23:06:19 - 2021-04-13 1:6:19）','正常',NULL),(8,'1617889690204','321',NULL,'外国专业技术（2021-04-12 23:06:19 - 2021-04-13 1:6:19）','缺勤',NULL),(9,'1617889690204','321','2','外国专业技术（2021-04-10 06:04:03 - 2021-04-10 8:4:3）','正常',NULL),(10,'1617889992784','32423','2','外国专业技术（2021-04-10 06:04:03 - 2021-04-10 8:4:3）','正常',NULL),(11,'1617889690204','321','2','外国专业技术（2021-04-10 06:04:03 - 2021-04-10 8:4:3）','正常',NULL),(12,'1617889992784','32423','2','外国专业技术（2021-04-10 06:04:03 - 2021-04-10 8:4:3）','正常',NULL),(13,'1617889992784','32423','2','外国专业技术（2021-04-10 06:04:03 - 2021-04-10 8:4:3）','已签到',NULL),(14,'1617889690204','321','2','外国专业技术（2021-04-10 06:04:03 - 2021-04-10 8:4:3）','已签到',NULL),(15,'1617889992784','32423','2','外国专业技术（2021-04-12 23:06:19 - 2021-04-13 1:6:19）','已签到',NULL),(16,'1617889690204','321','2','外国专业技术（2021-04-12 23:06:19 - 2021-04-13 1:6:19）','已签到',NULL),(17,'1617889992784','32423','2','外国专业技术（2021-04-12 23:06:19 - 2021-04-13 1:6:19）','未签到',NULL),(18,'1617889690204','321','2','外国专业技术（2021-04-12 23:06:19 - 2021-04-13 1:6:19）','未签到',NULL);

UNLOCK TABLES;

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `teacher_id` varchar(32) NOT NULL,
  `NAME` varchar(32) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `birthday` varchar(32) DEFAULT NULL COMMENT '出生日期',
  `POSITION` varchar(32) DEFAULT NULL COMMENT '职位',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `PASSWORD` varchar(32) DEFAULT NULL COMMENT '密码',
  `political` varchar(32) DEFAULT NULL COMMENT '入职时间',
  `tid_card` varchar(32) DEFAULT NULL COMMENT '身份证',
  `education` varchar(32) DEFAULT NULL COMMENT '学历',
  `home` varchar(32) DEFAULT NULL COMMENT '家庭住址',
  `state` varchar(10) DEFAULT NULL COMMENT '状态',
  `remarks` text COMMENT '备注',
  PRIMARY KEY (`teacher_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

LOCK TABLES `teacher` WRITE;

insert  into `teacher`(`teacher_id`,`NAME`,`sex`,`birthday`,`POSITION`,`phone`,`PASSWORD`,`political`,`tid_card`,`education`,`home`,`state`,`remarks`) values ('1617842546979','21321','男','2021-04-07','主管','21321321','1','2021-04-09','12321132','本科','3213','离职','21321'),('1617883891698','韦邦杠','男','2021-04-13','主管','3123213','1','2021-04-08','321321321312','本科','21321312','在职','321321');

UNLOCK TABLES;

/*Table structure for table `teacher_check` */

DROP TABLE IF EXISTS `teacher_check`;

CREATE TABLE `teacher_check` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT,
  `check_in_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `check_out_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `course_id` varchar(32) DEFAULT NULL COMMENT '课程表id',
  `course_name_s_e_time` varchar(320) DEFAULT NULL COMMENT '课程名称+开始-结束时间',
  `number_of_people` varchar(32) DEFAULT NULL COMMENT '应到人数',
  `number_of_real` varchar(32) DEFAULT NULL COMMENT '实到人数',
  `number_of_absence` varchar(32) DEFAULT NULL COMMENT '缺勤人数',
  `teracher_id` varchar(32) DEFAULT NULL COMMENT '教师ID',
  PRIMARY KEY (`UUID`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `teacher_check` */

LOCK TABLES `teacher_check` WRITE;

insert  into `teacher_check`(`UUID`,`check_in_time`,`check_out_time`,`course_id`,`course_name_s_e_time`,`number_of_people`,`number_of_real`,`number_of_absence`,`teracher_id`) values (1,'2021-04-13 00:00:00','2021-04-13 00:51:05','213','12321','321','21321','321321','21'),(2,'2021-04-13 01:03:43','2021-04-13 01:03:43','2','外国专业技术（2021-04-12 23:06:19 - 2021-04-13 1:6:19）','2','1','1','1617883891698'),(3,'2021-04-13 01:06:38','2021-04-13 01:06:38','2','外国专业技术（2021-04-10 06:04:03 - 2021-04-10 8:4:3）','2','2','0','1617883891698'),(4,'2021-04-13 01:08:11','2021-04-13 01:08:11','2','外国专业技术（2021-04-10 06:04:03 - 2021-04-10 8:4:3）','2','2','0','1617883891698'),(5,'2021-04-13 01:12:31','2021-04-13 01:12:31','2','外国专业技术（2021-04-10 06:04:03 - 2021-04-10 8:4:3）','2','0','2','1617883891698'),(6,'2021-04-13 01:12:45','2021-04-13 01:12:45','2','外国专业技术（2021-04-12 23:06:19 - 2021-04-13 1:6:19）','2','0','2','1617883891698'),(7,'2021-04-13 01:13:21','2021-04-13 01:13:21','2','外国专业技术（2021-04-12 23:06:19 - 2021-04-13 1:6:19）','2','0','2','1617883891698');

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
