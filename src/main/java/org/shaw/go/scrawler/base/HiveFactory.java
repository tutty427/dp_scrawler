package org.shaw.go.scrawler.base;

import org.shaw.go.scrawler.main.DPCrawlerHive;
import org.shaw.go.scrawler.bean.HiveConfig;

/**
 * Created by shawxy on 7/28/16.
 */
public class HiveFactory {


    private final static int DEFAULT_COUNT = 1;
    private final static Class DEFAULT_CLAZZ = DPCrawlerHive.class;

    private static CrawlerHive hive;


    private static Object key = new Object();



    public static CrawlerHive create(HiveConfig config){

        if(hive == null){
            synchronized (key){
                if(hive == null){
                    HiveConfig cfg = config;
                    if( cfg == null) {
                        cfg = new HiveConfig(DEFAULT_COUNT,DEFAULT_CLAZZ);
                    }
                    try {
                        hive = (CrawlerHive) cfg.getClazz().newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    hive.setConfig(cfg);
                }
            }
        }
        return hive;
    }




}
