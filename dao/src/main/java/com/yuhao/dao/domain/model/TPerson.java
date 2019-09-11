package com.yuhao.dao.domain.model;

import com.yuhao.dao.domain.vo.Location;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Collection;
import java.util.LinkedHashSet;

@Document
@Data
public class TPerson {
    @Id
    private String id;
    private String name;
    private Integer age;

    private Collection<Location> locations = new LinkedHashSet<Location>();

    public TPerson(String name,Integer age){
        super();
        this.age = age;
        this.name = name;
    }
}
