package com.app.repository;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class DatabaseAccess {
    public DatabaseAccess(){}

    @Autowired
    protected DataSource dataSource;

}
