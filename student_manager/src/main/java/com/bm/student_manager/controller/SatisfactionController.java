package com.bm.student_manager.controller;

import com.bm.student_manager.entity.Satisfaction;
import com.bm.student_manager.service.SatisfactionService;
import com.bm.student_manager.util.Result;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/satisfaction")
public class SatisfactionController {
    @Autowired
    private SatisfactionService satisfactionService;

    /**
     * 根据主键删除
     * 要求转入 aId
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(int id) {
        try {

            return satisfactionService.deleteByPrimaryKey(id) > 0 ? new Result().successMessage("删除成功") : Result.error("删除失败");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 添加对象satisfaction
     *
     * @param satisfaction
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Satisfaction satisfaction) {
        try {
            return satisfactionService.insert(satisfaction) > 0 ? new Result().successMessage("添加成功！") : Result.error("添加失败！");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }

    }

    /**
     * 根据主键查找对象  最多只能返回一个对象
     *
     * @param id
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(int id) {
        try {
            Satisfaction satisfaction1 = satisfactionService.selectByPrimaryKey(id);
            if (satisfaction1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(satisfaction1);
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        try {
            List<Satisfaction> list = satisfactionService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 根据主键修改全部字段
     *
     * @param satisfaction
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey")
    public Result updateByPrimaryKey(@RequestBody Satisfaction satisfaction) {
        try {
            return satisfactionService.updateByPrimaryKey(satisfaction) > 0 ? new Result().successMessage("修改成功") : Result.error("修改失败");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
    
    
     /* 查询所有数据分页
     *
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {
        try {
            PageHelper.startPage(page, limit);
            List<Satisfaction> list = satisfactionService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result(200, "ok", list, satisfactionService.count());
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
}
