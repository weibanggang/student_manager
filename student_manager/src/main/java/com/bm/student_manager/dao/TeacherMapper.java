package com.bm.student_manager.dao;

import com.bm.student_manager.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
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
    List<Teacher> selectByTeacher(@Param("teacherName") String teacherName,@Param("teacherUUID")  String teacherUUID);
    /**
     * 根据主键全部修改操作
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

    int countByTeacher(@Param("teacherName") String teacherName,@Param("teacherUUID")  String teacherUUID);
}