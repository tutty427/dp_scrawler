package org.shaw.go.scrawler.bean;

import lombok.Data;

/**
 * Created by shawxy on 7/28/16.
 */

@Data
public class HiveConfig {

    private int count;
    private Class clazz;

    public HiveConfig(){
        super();
    }

    public HiveConfig(int count,Class clazz){
        this.count = count;
        this.clazz = clazz;
    }


}
