package com.bm.student_manager.dao;

import com.bm.student_manager.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    /**
     * 根据主键删除操作
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

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
     * 根据主键全部修改操作
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