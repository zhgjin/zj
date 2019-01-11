package cn.sky999.controller;

import cn.sky999.common.Bean.ConvertUtils;
import cn.sky999.common.page.Page;
import cn.sky999.common.response.OperInfo;
import cn.sky999.common.spring.SpringUtils;
import cn.sky999.service.BaseServiceIntf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

@RestController
public class RestFulController {
    private static final Logger logger = LoggerFactory
            .getLogger(RestFulController.class);

    @RequestMapping("/rest/**")
    public OperInfo restApi(Page page,HttpServletRequest request,OperInfo operInfo) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        String[] ss=request.getRequestURI().split("/");
        BaseServiceIntf baseService=(BaseServiceIntf) SpringUtils.getBean(ss[2]+"Service");
        Assert.notNull(baseService,"找不到该类型");
        if(ss.length==4&&!"list".equals(ss[3])){
            operInfo.setVo(invoke(baseService,request.getMethod(),ss[3]));
        }else{
            operInfo.setVo(invoke(baseService,request.getMethod(),
                    resolveArgument(request,baseService.getClazz()), ss.length==4?page:null));
        }
        return operInfo;
    }

    public Object invoke(BaseServiceIntf baseService,String method,Object...param) throws IllegalAccessException {
        switch (method){
            case "GET": {
                if(param.length==1||param[1]==null){
                    if(param[0] instanceof String)
                        return baseService.findById((String)param[0]);
                    else
                        return baseService.findList(param[0]);
                } else{
                    return baseService.findPage(param[0],(Page)param[1]);
                }
            }
            case "PUT": baseService.update(param[0]);break;
            case "POST":baseService.add(param[0]);break;
            case "DELETE": baseService.delete((String)param[0]);break;
                default:throw new RuntimeException("不支持的方法类型");
        }
        return null;
    }

    public Object resolveArgument(HttpServletRequest request,Class clazz) throws IllegalAccessException, InstantiationException {
        Iterator<Map.Entry<String,String[]>> iterator=request.getParameterMap().entrySet().iterator();
        Object param=clazz.newInstance();
        while(iterator.hasNext()){
            Map.Entry<String,String[]> entry=iterator.next();
            try {
                Field field=clazz.getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(param, ConvertUtils.convert(entry.getValue()[0],field.getType()));
            } catch (Exception e) {
                logger.warn(entry.getKey()+"参数绑定失败");
                continue;
            }
        }
        return param;
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, ParseException {

        Date date=(Date)ConvertUtils.convert("2018-01-02",java.util.Date.class);
        System.out.println(date);
    }

}
