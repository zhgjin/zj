package cn.sky999.controller;

import cn.sky999.common.response.OperInfo;
import cn.sky999.entity.CodeInfo;
import cn.sky999.service.impl.CodeInfoServiceCloudImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class codeInfoController {
    @Autowired
    private CodeInfoServiceCloudImpl codeInfoService;

    @RequestMapping("/codeInfo/findList")
    public OperInfo findList(CodeInfo codeInfo ,OperInfo operInfo) throws IllegalAccessException {
        operInfo.setVo(codeInfoService.findList(codeInfo));
        return operInfo;
    }

}
