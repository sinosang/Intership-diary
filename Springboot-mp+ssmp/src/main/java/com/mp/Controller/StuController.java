package com.mp.Controller;

import ch.qos.logback.core.rolling.helper.FileStoreUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mp.Controller.utils.R;
import com.mp.Dto.StuDto;
import com.mp.Entity.Question;
import com.mp.Entity.Stu;
import com.mp.Entity.Tea;
import com.mp.Mapper.StuMapper;
import com.mp.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : zzy
 * @date : 2023/4/11 10:10
 */
@RestController
@RequestMapping("/school")
public class StuController {

    @Autowired
    private StuService stuService;

    @Autowired
    private TeaService teaService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/selectid")
    public Stu searchId(String id) {
        return stuService.getById(id);
    }

    //login登录
    //使用账号和密码来进行登录
    @PostMapping("/login")
    public R<Stu> login(HttpServletRequest request, @RequestBody Stu stu) {

        //1.对密码进行加密处理
        String password = stu.getPassword();
        //password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2.根据用户名查询数据库
        LambdaQueryWrapper<Stu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Stu::getUsername, stu.getUsername());
        Stu stu1 = stuService.getOne(queryWrapper);  //

        //3.如果没有查询到则返回登陆失败
        if (stu1 == null) {
            return R.error("不存在该用户");
        }
        //判断账号是否可用
        if (stu1.getDel_flag() == 1) {
            return R.error("该账号以停用");
        }

        //4.密码对比
        if (!stu1.getPassword().equals(password)) {
            return R.error("密码不一致");
        }

        //5.登录成功
        request.getSession().setAttribute("student", stu1.getUsername());
        return R.success(stu1);
    }

    //logout登出
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("student");
        return R.success("logout！！");
    }

    //新增学生
//    主键为id和username，id为生成的，username用户输入
//    设置id为primary，加密id确保唯一性
//    设置username为unique，用户手动输入，用unique确保字段的唯一
    @PostMapping("/addStu")
    public R<String> save(HttpServletRequest request, @RequestBody Stu stu) {
//       设置初始密码123
        stu.setPassword("123456");
//        类似于加密id，确保唯一
        String stuId = (String) request.getSession().getAttribute("student");
        stu.setId(stuId);
        stuService.save(stu);
        return R.success("add Student !!");
    }

    //    分页查询学生信息
    @GetMapping("/page/{page}/{pageSize}/{name}")
    public R<Page> page(@PathVariable int page, @PathVariable int pageSize, @PathVariable String name) {
//        分页构造器
        Page pageInfo = new Page(page, pageSize);
//        构造条件构造器
        LambdaQueryWrapper<Stu> queryWrapper = new LambdaQueryWrapper();

//        添加过滤条件,只查询出getUsername=输入的name的时候
        queryWrapper.like(StringUtils.isNotBlank(name), Stu::getUsername, name);
//        queryWrapper.select("username","teaId").like("username",name);
        queryWrapper.select(Stu.class, info -> !info.getColumn().equals("id") && !info.getColumn().equals("auto"));
//        添加排序条件
        queryWrapper.orderByAsc(Stu::getTeaId);
        stuService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    //    设置学生是否被禁用
    @PostMapping("/del_flag")
    public R<Stu> update_del(HttpServletRequest request, @RequestBody Stu stu) {
        stu.setDel_flag(1);
        stuService.updateById(stu);
//        只返回了有变动的数据，需要录入的id
        return R.success(stu);
    }

    //    根据学生姓名进行搜索(测试自行编写的方法)   使用RequestParam的方式，拼接url
    @GetMapping("/searchName")
    public R<Stu> searchName(HttpServletRequest request, @RequestParam("username") String username) {
        Stu res = stuService.SearchName(username);
        if (stuService.SearchName(username) == null) {
            return R.error("未查找到该用户");
        }
        System.out.println(stuService.SearchName(username));
        return R.success(res);
    }

    //教师端发放试卷
    @PostMapping("/setexam")
    public R<List<Stu>> setExam(@RequestParam("id") String id, @RequestParam("subject") String subject, @RequestParam("paper") String paper) {
        //设立wrapper为教师表中的内容
        //判断该id是否为教师id，以及是否拥有权限
        //通过使用count计数的方式，当匹配到有相同的id的时候，count=1，若未匹配到，则count=0
        //使用auth来判断用户是否拥有权限
        QueryWrapper <Tea> wrapper = new QueryWrapper<>();
        int count = teaService.count(wrapper.eq("id", id));
        System.out.println("count:"+count);
        int auth = teaService.count(wrapper.eq("auth",1));
        System.out.println("auth:"+auth);

        if (count != 1) {
            return R.error("不存在该id");
        }
        if (auth!=1){
            return R.error("该用户无权限不足");
        }

        List<Stu> res = stuService.setExam(id, subject, paper);
        return R.success(res);
    }

    //查看试卷
    @GetMapping("/get_exam")
    public R<StuDto> getExam(@RequestParam("id") String id){
       StuDto stuDto= questionService.getPaper(id);
        return R.success(stuDto);
    }

    //开始考试
    @GetMapping("examStart")
    public R<Stu> examStart(@RequestParam ("id") String id) throws InterruptedException {
        int flag = stuService.findFlag(id);
        System.out.println("flag:"+flag);
        if (flag == 0) {
            System.out.println("exam start now ");
            Thread.sleep(5000);
            stuService.updateMsg(id);
            Stu Fstu = stuService.getById(id);
            return R.success(Fstu);
        }
            return R.error("have finished the exam");
    }


}




