package com.bm.student_manager.service.impl;

import com.bm.student_manager.dao.ClassaMapper;
import com.bm.student_manager.entity.Classa;
import com.bm.student_manager.service.ClassaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassaServiceImpl implements ClassaService {

    @Autowired
    private ClassaMapper classaMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return classaMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param classa
     * @return
     */
    @Override
    public int insert(Classa classa) {
        return classaMapper.insert(classa);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public Classa selectByPrimaryKey(int id) {
        return classaMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<Classa> selectAll() {
        return classaMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param classa
     * @return
     */
    @Override
    public int updateByPrimaryKey(Classa classa) {
        return classaMapper.updateByPrimaryKey(classa);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return classaMapper.count();
    }
}
