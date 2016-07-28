package org.shaw.go.scrawler.bean;

/**
 * Created by shawxy on 7/28/16.
 */

import lombok.Data;

@Data
public class CallContext<T> {

    private T req;


}
