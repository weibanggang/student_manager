package com.bm.student_manager.service;

import com.bm.student_manager.entity.Student;

import java.util.List;

public interface StudentService {

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
     * @param student
     * @return
     */
    int insert(Student student);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    Student selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<Student> selectAll();

    /**
     * 修改操作
     *
     * @param student
     * @return
     */
    int updateByPrimaryKey(Student student);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}