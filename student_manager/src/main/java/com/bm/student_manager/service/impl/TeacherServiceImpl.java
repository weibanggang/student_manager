package com.bm.student_manager.service.impl;

import com.bm.student_manager.dao.TeacherMapper;
import com.bm.student_manager.entity.Teacher;
import com.bm.student_manager.service.TeacherService;
import com.bm.student_manager.util.Result;
import com.github.pagehelper.PageHelper;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String id) {
        return teacherMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param teacher
     * @return
     */
    @Override
    public int insert(Teacher teacher) {
        return teacherMapper.insert(teacher);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public Teacher selectByPrimaryKey(int id) {
        return teacherMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<Teacher> selectAll() {
        return teacherMapper.selectAll();
    }

    @Override
    public Result selectByTeacher(int page, int limit, String teacherName, String teacherUUID) {
        PageHelper.startPage(page, limit);
        List<Teacher> list = teacherMapper.selectByTeacher(teacherName,teacherUUID);
        if (list == null) {
            return new Result().successMessage("无数据");
        } else {
            return new Result(200, "ok", list, teacherMapper.countByTeacher(teacherName,teacherUUID));
        }
    }

    @Override
    public Result login(String password, String teacherId) {
        Teacher teacher = teacherMapper.login(password,teacherId);
        if(teacher == null){
            return new Result().error("账号密码错误！");
        }

        return new Result(200, "ok", teacher);
    }

    /**
     * 修改操作
     *
     * @param teacher
     * @return
     */
    @Override
    public int updateByPrimaryKey(Teacher teacher) {
        return teacherMapper.updateByPrimaryKey(teacher);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return teacherMapper.count();
    }
}
