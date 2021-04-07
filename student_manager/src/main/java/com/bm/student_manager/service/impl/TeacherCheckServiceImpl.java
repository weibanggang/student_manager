package com.bm.student_manager.service.impl;

import com.bm.student_manager.dao.TeacherCheckMapper;
import com.bm.student_manager.entity.TeacherCheck;
import com.bm.student_manager.service.TeacherCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherCheckServiceImpl implements TeacherCheckService {

    @Autowired
    private TeacherCheckMapper teacherCheckMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return teacherCheckMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param teacherCheck
     * @return
     */
    @Override
    public int insert(TeacherCheck teacherCheck) {
        return teacherCheckMapper.insert(teacherCheck);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public TeacherCheck selectByPrimaryKey(int id) {
        return teacherCheckMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<TeacherCheck> selectAll() {
        return teacherCheckMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param teacherCheck
     * @return
     */
    @Override
    public int updateByPrimaryKey(TeacherCheck teacherCheck) {
        return teacherCheckMapper.updateByPrimaryKey(teacherCheck);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return teacherCheckMapper.count();
    }
}
