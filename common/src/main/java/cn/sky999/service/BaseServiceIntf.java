package cn.sky999.service;

import cn.sky999.common.page.Page;

public interface BaseServiceIntf<T> {
    public Class getClazz();

    public Object findById(String id);

    public Object findList(T param) throws IllegalAccessException ;

    public Object findPage(T param,Page page) throws IllegalAccessException;

    public void delete(T t);

    public void delete(String id);

    public void update(T t);

    public void add(T t);
}
