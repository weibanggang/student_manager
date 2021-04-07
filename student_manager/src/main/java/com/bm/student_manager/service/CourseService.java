package com.bm.student_manager.service;

import com.bm.student_manager.entity.Course;

import java.util.List;

public interface CourseService {

    /**
     * 删除操作 根据主键
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(int id);

    /**
     * 添加操作
     *
     * @param course
     * @return
     */
    int insert(Course course);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    Course selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<Course> selectAll();
    /**
     * 全部查询操作
     *
     * @return
     */
    List<Course> selectPage(String courseType,String courseName);
    int countPage(String courseType,String courseName);

    /**
     * 修改操作
     *
     * @param course
     * @return
     */
    int updateByPrimaryKey(Course course);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}