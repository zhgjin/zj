package cn.sky999.controller;

import cn.sky999.common.response.OperInfo;
import cn.sky999.entity.CodeInfo;
import cn.sky999.service.CodeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CodeInfoController {
    @Autowired
    private CodeInfoService codeInfoService;
    private String url="http://eruka-provider:8081";

    @RequestMapping("/codeInfo/findList")
    public OperInfo findList(CodeInfo codeInfo,OperInfo operInfo) throws IllegalAccessException {
        operInfo.setVo(codeInfoService.findList(codeInfo));
        return operInfo;
    }

}
