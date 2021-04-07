package com.bm.student_manager.dao;

import com.bm.student_manager.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {
    /**
     * 根据主键删除操作
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
     * 根据主键全部修改操作
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