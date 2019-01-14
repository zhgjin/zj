package cn.sky999.controller;

import cn.sky999.common.response.OperInfo;
import cn.sky999.common.spring.SpringUtils;
import cn.sky999.entity.CodeInfo;
import cn.sky999.service.CodeInfoService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CodeInfoController {
    @Reference(version = "1.0.0")
    private CodeInfoService codeInfoService;

    @RequestMapping("test")
    public OperInfo test(OperInfo operInfo) throws IllegalAccessException {
        operInfo.setVo(codeInfoService.findList(new CodeInfo()));
        return operInfo;
    }

    @RequestMapping("test2")
    public OperInfo test2(OperInfo operInfo){
        operInfo.setVo(SpringUtils.getBean("codeInfoService"));
        return operInfo;
    }

}
