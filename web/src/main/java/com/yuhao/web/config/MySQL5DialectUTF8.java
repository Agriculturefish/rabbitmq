
package com.yuhao.web.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * spring.jpa.properties.hibernate.dialect=指定服务类
 * */
public class MySQL5DialectUTF8 extends MySQL5InnoDBDialect{  
	  
    @Override  
    public String getTableTypeString() {  
        return " ENGINE=InB DEFAULT CHARSET=utf8";
    }  
}  

      
