package com.bm.student_manager.service;

import com.bm.student_manager.entity.Teacher;
import com.bm.student_manager.util.Result;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TeacherService {

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
     * @param teacher
     * @return
     */
    int insert(Teacher teacher);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    Teacher selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<Teacher> selectAll();
    /**
     * 全部查询操作
     *
     * @return
     */
    Result selectByTeacher(int page, int limit,String teacherName, String teacherUUID);

    /**
     * 修改操作
     *
     * @param teacher
     * @return
     */
    int updateByPrimaryKey(Teacher teacher);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}