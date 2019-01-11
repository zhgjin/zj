package cn.sky999.service.impl;

import cn.sky999.annotation.Condition;
import cn.sky999.annotation.ConditionType;
import cn.sky999.service.BaseServiceIntf;
import cn.sky999.common.page.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class BaseService<T> implements BaseServiceIntf<T>{
    @Autowired
    private Mapper<T> mapper;
    private Class clazz;

    public BaseService(){
        getClazz();
    }

    @Override
    public Class getClazz(){
        ParameterizedType type = (ParameterizedType)((Type) this.getClass().getGenericSuperclass());
        this.clazz=(Class)type.getActualTypeArguments()[0];
        return this.clazz;
}

    @Override
    public T findById(String id){
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> findList(T param) throws IllegalAccessException {
        Example example=new Example(this.clazz);
        Example.Criteria c = example.createCriteria();
        generatCondition(param,c);
        return mapper.selectByExample(example);
    }

    @Override
    public PageInfo<T> findPage(T param,Page page) throws IllegalAccessException {
        PageHelper.startPage(page.getPage(),page.getRows());
        return PageInfo.of(this.findList(param));
    }

    @Override
    public void delete(T t){
        mapper.delete(t);
    }

    @Override
    public void delete(String id){
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(T t){
        mapper.updateByPrimaryKey(t);
    }

    @Override
    public void add(T t){
        mapper.insert(t);
    }

    public void generatCondition(T param,Example.Criteria c) throws IllegalAccessException {
        Field[] fields=this.clazz.getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            Object obj=field.get(param);
            Condition condition=(Condition)field.getAnnotation(Condition.class);
            Column column=(Column)field.getAnnotation(Column.class);

            if(obj==null||(condition==null&&column==null)) continue;
            String columnName=column==null?condition.column():column.name();
            if(StringUtils.isEmpty(columnName)) continue;
            String cstr= ConditionType.EQ;
            if(condition != null) cstr=condition.value();
            if(cstr.equals(ConditionType.LIKE)) obj="%"+obj.toString()+"%";
            c.andCondition(columnName+cstr,obj);
        }
    }
}
