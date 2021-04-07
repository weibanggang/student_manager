package com.bm.student_manager.service.impl;

import com.bm.student_manager.dao.StudentCheckMapper;
import com.bm.student_manager.entity.StudentCheck;
import com.bm.student_manager.service.StudentCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCheckServiceImpl implements StudentCheckService {

    @Autowired
    private StudentCheckMapper studentCheckMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return studentCheckMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param studentCheck
     * @return
     */
    @Override
    public int insert(StudentCheck studentCheck) {
        return studentCheckMapper.insert(studentCheck);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public StudentCheck selectByPrimaryKey(int id) {
        return studentCheckMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<StudentCheck> selectAll() {
        return studentCheckMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param studentCheck
     * @return
     */
    @Override
    public int updateByPrimaryKey(StudentCheck studentCheck) {
        return studentCheckMapper.updateByPrimaryKey(studentCheck);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return studentCheckMapper.count();
    }
}
