package com.bm.student_manager.service.impl;

import com.bm.student_manager.dao.StudentMapper;
import com.bm.student_manager.entity.Student;
import com.bm.student_manager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return studentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param student
     * @return
     */
    @Override
    public int insert(Student student) {
        return studentMapper.insert(student);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public Student selectByPrimaryKey(int id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param student
     * @return
     */
    @Override
    public int updateByPrimaryKey(Student student) {
        return studentMapper.updateByPrimaryKey(student);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return studentMapper.count();
    }
}
