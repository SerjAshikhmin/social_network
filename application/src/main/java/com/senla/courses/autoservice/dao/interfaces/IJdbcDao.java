package com.senla.courses.autoservice.dao.interfaces;

import com.lib.dicontainer.annotations.Singleton;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public interface IJdbcDao<T> {

    int insert(T obj) throws SQLException;
    int delete(T obj) throws SQLException;
    int update(T obj) throws SQLException;
    T findById(int id) throws SQLException;
    List<T> findAll() throws SQLException;
}
