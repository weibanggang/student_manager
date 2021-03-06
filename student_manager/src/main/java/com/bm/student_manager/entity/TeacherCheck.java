package com.bm.student_manager.entity;

public class TeacherCheck {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_check.UUID
     *
     * @mbg.generated
     */
    private String uuid;
    private String teacherId;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_check.check_in_time
     *
     * @mbg.generated
     */
    private String checkInTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_check.check_out_time
     *
     * @mbg.generated
     */
    private String checkOutTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_check.course_id
     *
     * @mbg.generated
     */
    private String courseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_check.course_name_s_e_time
     *
     * @mbg.generated
     */
    private String courseNameSETime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_check.number_of_people
     *
     * @mbg.generated
     */
    private String numberOfPeople;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_check.number_of_real
     *
     * @mbg.generated
     */
    private String numberOfReal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_check.number_of_absence
     *
     * @mbg.generated
     */
    private String numberOfAbsence;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_check.UUID
     *
     * @return the value of teacher_check.UUID
     *
     * @mbg.generated
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_check.UUID
     *
     * @param uuid the value for teacher_check.UUID
     *
     * @mbg.generated
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_check.check_in_time
     *
     * @return the value of teacher_check.check_in_time
     *
     * @mbg.generated
     */
    public String getCheckInTime() {
        return checkInTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_check.check_in_time
     *
     * @param checkInTime the value for teacher_check.check_in_time
     *
     * @mbg.generated
     */
    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime == null ? null : checkInTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_check.check_out_time
     *
     * @return the value of teacher_check.check_out_time
     *
     * @mbg.generated
     */
    public String getCheckOutTime() {
        return checkOutTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_check.check_out_time
     *
     * @param checkOutTime the value for teacher_check.check_out_time
     *
     * @mbg.generated
     */
    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime == null ? null : checkOutTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_check.course_id
     *
     * @return the value of teacher_check.course_id
     *
     * @mbg.generated
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_check.course_id
     *
     * @param courseId the value for teacher_check.course_id
     *
     * @mbg.generated
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_check.course_name_s_e_time
     *
     * @return the value of teacher_check.course_name_s_e_time
     *
     * @mbg.generated
     */
    public String getCourseNameSETime() {
        return courseNameSETime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_check.course_name_s_e_time
     *
     * @param courseNameSETime the value for teacher_check.course_name_s_e_time
     *
     * @mbg.generated
     */
    public void setCourseNameSETime(String courseNameSETime) {
        this.courseNameSETime = courseNameSETime == null ? null : courseNameSETime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_check.number_of_people
     *
     * @return the value of teacher_check.number_of_people
     *
     * @mbg.generated
     */
    public String getNumberOfPeople() {
        return numberOfPeople;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_check.number_of_people
     *
     * @param numberOfPeople the value for teacher_check.number_of_people
     *
     * @mbg.generated
     */
    public void setNumberOfPeople(String numberOfPeople) {
        this.numberOfPeople = numberOfPeople == null ? null : numberOfPeople.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_check.number_of_real
     *
     * @return the value of teacher_check.number_of_real
     *
     * @mbg.generated
     */
    public String getNumberOfReal() {
        return numberOfReal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_check.number_of_real
     *
     * @param numberOfReal the value for teacher_check.number_of_real
     *
     * @mbg.generated
     */
    public void setNumberOfReal(String numberOfReal) {
        this.numberOfReal = numberOfReal == null ? null : numberOfReal.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_check.number_of_absence
     *
     * @return the value of teacher_check.number_of_absence
     *
     * @mbg.generated
     */
    public String getNumberOfAbsence() {
        return numberOfAbsence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_check.number_of_absence
     *
     * @param numberOfAbsence the value for teacher_check.number_of_absence
     *
     * @mbg.generated
     */
    public void setNumberOfAbsence(String numberOfAbsence) {
        this.numberOfAbsence = numberOfAbsence == null ? null : numberOfAbsence.trim();
    }
}