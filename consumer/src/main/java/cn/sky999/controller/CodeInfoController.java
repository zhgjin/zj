package cn.sky999.controller;

import cn.sky999.common.response.OperInfo;
import cn.sky999.entity.CodeInfo;
import cn.sky999.service.CodeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CodeInfoController {
    @Resource
    private CodeInfoService codeInfoService;

    @RequestMapping("test")
    public OperInfo test(OperInfo operInfo) throws IllegalAccessException {
        operInfo.setVo(codeInfoService.findList(new CodeInfo()));
        return operInfo;
    }
}
