package cn.sky999.service;

import cn.sky999.common.page.Page;
import cn.sky999.entity.CodeInfo;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="eruka-provider")
public interface CodeInfoService  extends BaseServiceIntf<CodeInfo>{
    @RequestLine(value = "/rest/codeInfo")
    public Object findList();
    /*@GetMapping("/rest/codeInfo/{id}")
    public Object findById(@PathVariable String id);*/
    /*@GetMapping("/rest/codeInfo/list")
    public Object findPage(CodeInfo param,Page page);*/

}
