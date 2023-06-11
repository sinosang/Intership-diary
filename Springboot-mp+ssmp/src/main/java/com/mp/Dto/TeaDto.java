package com.mp.Dto;

import com.mp.Entity.Stu;
import com.mp.Entity.Tea;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zzy
 * @date : 2023/4/17 17:34
 */
@Data
public class TeaDto extends Tea {
//   数据传输对象
//   存储students内容
   private List<Stu> teaStudents = new ArrayList<>();
}
