package com.bm.student_manager.service.impl;

import com.bm.student_manager.dao.CourseMapper;
import com.bm.student_manager.entity.Course;
import com.bm.student_manager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return courseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param course
     * @return
     */
    @Override
    public int insert(Course course) {
        return courseMapper.insert(course);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public Course selectByPrimaryKey(int id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<Course> selectAll() {
        return courseMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param course
     * @return
     */
    @Override
    public int updateByPrimaryKey(Course course) {
        return courseMapper.updateByPrimaryKey(course);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return courseMapper.count();
    }
}
