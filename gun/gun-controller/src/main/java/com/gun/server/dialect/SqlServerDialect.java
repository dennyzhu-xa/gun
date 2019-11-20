package com.gun.server.dialect;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL81Dialect;

public class SqlServerDialect extends PostgreSQL81Dialect {
	public SqlServerDialect() {  
        super();  
        registerHibernateType(Types.VARCHAR, "string");  
        registerHibernateType(Types.DECIMAL, "bigdecimal");
        registerHibernateType(Types.NUMERIC, "double");
        registerHibernateType(Types.CHAR,"char");
    }

}
