package cn.sky999.service.impl;

import cn.sky999.entity.CodeInfo;
import cn.sky999.service.CodeInfoService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Component("codeInfoService")
@Service(version = "1.0.0")
public class CodeInfoServiceImpl extends BaseService<CodeInfo> implements CodeInfoService{
}
