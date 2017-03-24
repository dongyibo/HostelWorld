package com.nju.hostelworld.dao;

import org.hibernate.Session;

import java.util.Date;
import java.util.List;

/**
 * Created by dongyibo on 2017/1/4.
 */
public interface BaseDao {

    public Session getSession();

    public Object get(Class c, int id);

    public Object get(Class c, String id);

    public Object getByValue(Class c, String column, String value);

    public List getListWithTime(Class c, String time, Date start, Date end);

    public List getListWithCondition(Class c, String column, int value);

    public List getAllList(String hql, String value);

    public Long getTotalCount(Class c);

    public void deleteById(Class c, int id);

    public void deleteByList(Class c, int[] ids);

    public void deleteByModel(Object model);

    public void save(Object bean);

    public void update(Object model);

}
