package com.nju.hostelworld.dao.impl;

import com.nju.hostelworld.dao.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by dongyibo on 2017/1/4.
 */

@Repository
public class BaseDaoImpl implements BaseDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Object get(Class c, int id) {
        Session session = this.getSession();
        return session.get(c, id);
    }

    @Override
    public Object get(Class c, String id) {
        Session session = this.getSession();
        return session.get(c, id);
    }

    @Override
    public Object getByValue(Class c, String column, String value) {
        Session session = this.getSession();
        Criteria criteria = session.createCriteria(c);
        criteria.add(Expression.eq(column, value));
        List list = criteria.list();
        if (list.size() == 0) return null;
        return list.get(0);
    }

    @Override
    public List getListWithTime(Class c, String time, Date start, Date end) {
        Session session = this.getSession();
        Criteria criteria = session.createCriteria(c);
        List list = criteria.add(Expression.between(time, start, end)).addOrder(Order.desc(time)).list();
        if (list.size() == 0) return null;
        return list;
    }

    @Override
    public List getListWithCondition(Class c, String column, int value) {
        Session session = this.getSession();
        Criteria criteria = session.createCriteria(c);
        criteria.add(Expression.eq(column, value));
        List list = criteria.addOrder(Order.asc("id")).list();
        if (list.size() == 0) return null;
        return list;
    }

    @Override
    public List getAllList(String hql, String value) {
        Session session = this.getSession();
        Query query = session.createQuery(hql).setString(0, value);
        return query.list();
    }

    @Override
    public Long getTotalCount(Class c) {
        Session session = this.getSession();
        String hql = "select count(*) from " + c.getName();
        Long count = (Long) session.createQuery(hql).uniqueResult();
        return count != null ? count.longValue() : 0;
    }

    @Override
    public void deleteById(Class c, int id) {
        Session session = this.getSession();
        Object obj = session.get(c, id);
        session.delete(obj);
    }

    @Override
    public void deleteByModel(Object model) {
        Session session = this.getSession();
        session.delete(model);
    }

    @Override
    public void deleteByList(Class c, int[] ids) {
        for (int id : ids) {
            Object obj = this.getSession().get(c, id);
            if (obj != null) {
                this.getSession().delete(obj);
            }
        }
    }

    @Override
    public void save(Object model) {
        Session session = this.getSession();
        session.save(model);
    }

    @Override
    public void update(Object model) {
        Session session = this.getSession();
        session.update(model);
    }

}
