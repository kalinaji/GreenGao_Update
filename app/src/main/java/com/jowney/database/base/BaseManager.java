package com.jowney.database.base;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Manager基类
 */
public class BaseManager<T, K> {

    private AbstractDao<T, K> mDao;

    public BaseManager(AbstractDao dao) {
        mDao = dao;
    }

    /**
     * 存储一个对象
     * @param item
     */
    public void save(T item) {
        mDao.insert(item);
    }

    /**
     * 存储多个对象
     * @param items
     */
    public void save(T... items) {
        mDao.insertInTx(items);
    }

    /**
     * List方式 存储多个对象
     * @param items
     */
    public void save(List<T> items) {
        mDao.insertInTx(items);
    }


    /**
     * 存储/更新 一个对象
     * @param item
     */
    public void saveOrUpdate(T item) {
        mDao.insertOrReplace(item);
    }

    /**
     * 存储/更新 多个对象
     * @param items
     */
    public void saveOrUpdate(T... items) {
        mDao.insertOrReplaceInTx(items);
    }

    /**
     * List方式 存储/更新 多个对象
     * @param items
     */
    public void saveOrUpdate(List<T> items) {
        mDao.insertOrReplaceInTx(items);
    }


    public void deleteByKey(K key) {
        mDao.deleteByKey(key);
    }

    /**
     * 删除 一个对象
     * @param item
     */
    public void delete(T item) {
        mDao.delete(item);
    }

    /**
     * 删除 多个对象
     * @param items
     */
    public void delete(T... items) {
        mDao.deleteInTx(items);
    }

    /**
     * List方式 删除 多个对象
     * @param items
     */
    public void delete(List<T> items) {
        mDao.deleteInTx(items);
    }

    /**
     * 删除全部
     */
    public void deleteAll() {
        mDao.deleteAll();
    }


    /**
     * 更新 一个对象
     * @param item
     */
    public void update(T item) {
        mDao.update(item);
    }

    /**
     * 更新 多个对象
     * @param items
     */
    public void update(T... items) {
        mDao.updateInTx(items);
    }

    /**
     * List方式 更新多个对象
     * @param items
     */
    public void update(List<T> items) {
        mDao.updateInTx(items);
    }


    public T query(K key) {
        return mDao.load(key);
    }

    /**
     * 查询全部
     */
    public List<T> queryAll() {
        return mDao.loadAll();
    }

    /**
     * 查询
     * @param where
     * @param params
     */
    public List<T> query(String where, String... params) {
        return mDao.queryRaw(where, params);
    }

    public QueryBuilder<T> queryBuilder() {
        return mDao.queryBuilder();
    }

    public long count() {
        return mDao.count();
    }

    public void refresh(T item) {
        mDao.refresh(item);
    }

    public void detach(T item) {
        mDao.detach(item);
    }
}
