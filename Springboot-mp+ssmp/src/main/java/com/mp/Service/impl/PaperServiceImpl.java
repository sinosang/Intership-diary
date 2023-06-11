package com.mp.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mp.Entity.Paper;
import com.mp.Mapper.PaperMapper;
import com.mp.Service.PaperService;
import org.springframework.stereotype.Service;

/**
 * @author : zzy
 * @date : 2023/5/5 16:58
 */
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {
}
