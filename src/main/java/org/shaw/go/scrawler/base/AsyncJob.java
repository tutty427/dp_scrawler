package org.shaw.go.scrawler.base;

import java.util.concurrent.Callable;

/**
 * Created by shawxy on 7/28/16.
 */
public class AsyncJob implements Callable<Boolean> {

    private Scrawler scrawler;


    public AsyncJob(Scrawler scrawler){

        this.scrawler = scrawler;

    }

    public Boolean call() throws Exception {
        try {
            scrawler.doScraw();
        }catch(Exception e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
