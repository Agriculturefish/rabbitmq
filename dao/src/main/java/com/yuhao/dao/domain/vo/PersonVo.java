package com.yuhao.dao.domain.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class PersonVo implements Serializable{
    private static final long  serialVersionUID = 1L;

    private String id;
    private String name;
    private Integer age;

    public PersonVo(){
        super();
    }

    public PersonVo(String id,String name,Integer age){
        super();
        this.id = id;
        this.age = age;
        this.name = name;
    }


}
