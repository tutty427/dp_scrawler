package org.shaw.go.scrawler.base;


import org.shaw.go.scrawler.bean.HiveConfig;

import java.util.concurrent.*;

/**
 * Created by shawxy on 7/27/16.
 */
public abstract class BaseCrawlerHive implements CrawlerHive{


    private static ExecutorService HOME;
    private static HiveConfig config;




    public void setConfig(HiveConfig config) {

        if(config == null ){
            this.config = new HiveConfig();
        }

        this.config = config;
    }


    public Integer getCrawlers(){
        return config.getCount();
    }

    public ExecutorService getHome(){

        if(HOME == null) {

            synchronized (this){

                if(HOME == null) {


                    if( config == null || getCrawlers() < 0 ){

                        HOME = Executors.newFixedThreadPool(1);
                    }
                    HOME = Executors.newFixedThreadPool(getCrawlers());


                }


            }

        }


        return HOME;
    }

    public abstract void goCraw() ;
}
