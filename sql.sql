
CREATE DATABASE student_manager;
USE student_manager;
CREATE TABLE student(
student_id VARCHAR(32) PRIMARY KEY ,
classs VARCHAR(32) COMMENT '班级',
NAME VARCHAR(32) COMMENT '姓名',
sex VARCHAR(2) COMMENT '性别',
birthday VARCHAR(32) COMMENT '出生日期',
address VARCHAR(32) COMMENT '家庭住址',
PASSWORD VARCHAR(32) COMMENT '密码',
parents_name VARCHAR(15) COMMENT '家长姓名',
parents_number VARCHAR(11) COMMENT '家长号码',
number VARCHAR(11) COMMENT '手机号码',
sentrancedate VARCHAR(32) COMMENT '入学时间',
qq VARCHAR(32) COMMENT '邮箱地址',
state VARCHAR(10) COMMENT '状态',
admissions_teacher VARCHAR(32) COMMENT '招生老师',
grade VARCHAR(32) COMMENT '学员等级',
remarks TEXT COMMENT '备注'
);

CREATE TABLE teacher(
teacher_id VARCHAR(32) PRIMARY KEY ,
NAME VARCHAR(32) COMMENT '姓名',
sex VARCHAR(2) COMMENT '性别',
birthday VARCHAR(32) COMMENT '出生日期',
POSITION VARCHAR(32) COMMENT '职位',
phone VARCHAR(11) COMMENT '电话号码',
PASSWORD VARCHAR(32) COMMENT '密码',
political VARCHAR(32) COMMENT '入职时间',
tid_card VARCHAR(32) COMMENT '身份证',
education VARCHAR(32) COMMENT '学历',
home VARCHAR(32) COMMENT '家庭住址',
state VARCHAR(10) COMMENT '状态',
remarks TEXT COMMENT '备注'
);
CREATE TABLE course(
UUID VARCHAR(32) PRIMARY KEY ,
course_type VARCHAR(32) COMMENT '课程类型',
course_name VARCHAR(32) COMMENT '课程名称',
remarks TEXT COMMENT '备注'
);
CREATE TABLE class_schedule_card(
UUID VARCHAR(32) PRIMARY KEY ,
course_id VARCHAR(32) COMMENT '所属课程id',
teacher_id VARCHAR(32) COMMENT '任课老师id',
teacher_name VARCHAR(32) COMMENT '任课老师名字',
course_type VARCHAR(32) COMMENT '课程类型',
course_name VARCHAR(32) COMMENT '课程名称',
start_time VARCHAR(32) COMMENT '开始时间',
end_time VARCHAR(32) COMMENT '结束时间',
WEEK VARCHAR(32) COMMENT '星期',
STATUS VARCHAR(32) COMMENT '状态',
remarks TEXT COMMENT '备注'
);
CREATE TABLE classa(
UUID VARCHAR(32) PRIMARY KEY ,
class_type VARCHAR(32) COMMENT '班级类别',
class_name VARCHAR(32) COMMENT '班级名称',
teacher_id VARCHAR(32) COMMENT '班主任id',
teacher_name VARCHAR(32) COMMENT '班主任姓名',
phone VARCHAR(32) COMMENT '联系电话',
start_class_time VARCHAR(32) COMMENT '开班日期',
end_class_time VARCHAR(32) COMMENT '结班日期',
expect_number_people VARCHAR(32) COMMENT '预招人数',
actual_number_people VARCHAR(32) COMMENT '实际人数',
plan_number VARCHAR(32) COMMENT '计划课时',
already_number VARCHAR(32) COMMENT '已上课时',
course_id VARCHAR(32) COMMENT '课程id',
course_name VARCHAR(32) COMMENT '课程名称',
remarks TEXT COMMENT '备注'
);
CREATE TABLE teacher_check(
UUID VARCHAR(32) PRIMARY KEY ,
check_in_time VARCHAR(32) COMMENT '签到时间',
check_out_time VARCHAR(32) COMMENT '签退时间',
course_id VARCHAR(32) COMMENT '课程表id',
course_name_s_e_time VARCHAR(32) COMMENT '课程名称+开始-结束时间',
number_of_people VARCHAR(32) COMMENT '应到人数',
number_of_real VARCHAR(32) COMMENT '实到人数',
number_of_absence VARCHAR(32) COMMENT '缺勤人数'
);
CREATE TABLE student_check(
UUID VARCHAR(32) PRIMARY KEY ,
student_id VARCHAR(32) COMMENT '学生ID',
student_name VARCHAR(32) COMMENT '学生姓名',
sourse_id VARCHAR(32) COMMENT '课程表ID',
course_name_s_e_time VARCHAR(32) COMMENT '课程名称+开始-结束时间',
STATUS VARCHAR(32) COMMENT '考勤状态',
remarks TEXT COMMENT '备注'
);
CREATE TABLE satisfaction(
UUID VARCHAR(32) PRIMARY KEY ,
TIME VARCHAR(32) COMMENT '调查时间',
content VARCHAR(32) COMMENT '调查内容',
investigator VARCHAR(32) COMMENT '调查人',
respondents VARCHAR(32) COMMENT '被调查人',
survey_satisfaction VARCHAR(32) COMMENT '调查满意度',
remarks TEXT COMMENT '备注'
);



