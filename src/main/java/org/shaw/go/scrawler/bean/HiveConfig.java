package org.shaw.go.scrawler.bean;

import lombok.Data;

/**
 * Created by shawxy on 7/28/16.
 */

@Data
public class HiveConfig {

    private int count;

    public HiveConfig(){
        super();
    }

    public HiveConfig(int count){
        this.count = count;
    }


}
