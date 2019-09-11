package com.yuhao.dao.domain.vo;

import lombok.Data;

@Data
public class Location {
    private String place;
    private String year;

    public Location(String place,String year){
        super();
        this.place = place;
        this.year = year;
    }
}
