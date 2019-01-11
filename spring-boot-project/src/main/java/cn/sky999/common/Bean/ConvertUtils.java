package cn.sky999.common.Bean;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 类型转换工具
 */
public class ConvertUtils extends org.apache.commons.beanutils.ConvertUtils{
    static{
        org.apache.commons.beanutils.ConvertUtils.register(new Converter() {
            @Override
            public <Date>  Date convert(Class<Date> aClass, Object o) {
                java.util.Date date;
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                if(o instanceof String){
                    try {
                        date=sdf.parse(o.toString());
                    } catch (ParseException e) {
                        sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            date=sdf.parse(o.toString());
                        } catch (ParseException e1) {
                            return null;
                        }
                    }
                    return (Date) date;
                }
                return null;
            }
        }, java.util.Date.class);
    }

    public static Object convert(String value, Class<?> clazz) {
        return org.apache.commons.beanutils.ConvertUtils.convert(value,clazz);
    }
}
