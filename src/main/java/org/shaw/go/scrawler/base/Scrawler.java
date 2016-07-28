package org.shaw.go.scrawler.base;

import org.shaw.go.scrawler.bean.CallContext;

/**
 * Created by shawxy on 7/28/16.
 */
public interface Scrawler {

    void doScraw();


    void setContext(CallContext context);

}
