package com.mp.Controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mp.Controller.utils.R;
import com.mp.Dto.TeaDto;
import com.mp.Entity.Paper;
import com.mp.Entity.Question;
import com.mp.Entity.Stu;
import com.mp.Entity.Tea;
import com.mp.Service.PaperService;
import com.mp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import com.mp.Service.TeaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author : zzy
 * @date : 2023/4/17 15:06
 */
@RestController
@RequestMapping("/teacher")
public class TeaController {

    @Autowired
    private TeaService teaService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private PaperService paperService;


    //查询老师带的学生
    @GetMapping("/unitsearch")
    public R<TeaDto> searchName(@RequestParam("id") String id) {
        TeaDto teaDto = teaService.UnitSearch(id);

        return R.success(teaDto);
    }

    //上传文件
    @Value("${reggie.path}")
    private String basePath;

    @PostMapping(value = "/upload")
    public R<String> upload(MultipartFile file, @RequestParam("id") String teaId) {
        //用于判断是否输入了文件
        try {
            String res = file.toString();
        } catch (Exception e) {
            return R.error("录入的文件为空");
        }
        //用于判断输入的用户是否存在
        try {
            String res = teaService.getById(teaId).toString();
        } catch (NullPointerException e) {
            return R.error("录入的用户不存在");
        }

        //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
        System.out.println((file.toString()));

        //原始文件名，用.作为分隔符
        String originalFilename = file.getOriginalFilename();//abc.jpg
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
        String fileName = teaId + "_" + UUID.randomUUID().toString() + suffix;//dfsdfdfd.jpg

        //创建一个目录对象
        File dir = new File(basePath + teaId + "//");
        //判断当前目录是否存在
        if (!dir.exists()) {
            //目录不存在，需要创建
            dir.mkdirs();
        }

        try {
            //将临时文件转存到指定位置
            file.transferTo(new File(basePath + teaId + "//" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    }


    //修改题目
    @PostMapping("quesChange")
    public R<List<Question>> QuestionModify(@RequestParam("TeaId")String TeaId, @RequestParam("QuestionId")String QuestionId, @RequestParam("subject")String subject, @RequestParam("paper")String paper, @RequestParam("value")String value){

        QueryWrapper<Tea> TeaWrapper = new QueryWrapper<>();
        int count = teaService.count(TeaWrapper.eq("id", TeaId));
        int auth = teaService.count(TeaWrapper.eq("auth",1));
        if (count != 1) {
            return R.error("不存在该id");
        }
        if (auth!=1){
            return R.error("该用户无权限不足");
        }
        List<Question> res=questionService.ChangeQuestion(QuestionId,subject,paper,value,TeaId);
        return R.success(res);
    }

    //查看题库题库
    @GetMapping("lookExam")
    public R<List<Question>> LookQuestion(@RequestParam("subject")String subject,@RequestParam(name = "paper",required=false)String paper){
        LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Question::getSubject,subject);
        System.out.println(paper);
        if (paper==null){
            List<Question> questions = questionService.list(wrapper);
            return R.success(questions);
        }
        wrapper.eq(Question::getPaper,paper);
        List<Question> questions = questionService.list(wrapper);
        return R.success(questions);
    }

    //查看该试卷有几份试卷
    @GetMapping("lookSubject")
    public R<List<Paper>> LookPaper(@RequestParam("subject")String subject){
        LambdaQueryWrapper <Paper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Paper::getSubject,subject);
        List<Paper> papers = paperService.list(wrapper);
        return R.success(papers);

    }



}
