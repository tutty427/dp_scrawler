package org.shaw.go.scrawler.base;

import org.shaw.go.scrawler.bean.HiveConfig;

import java.util.concurrent.ExecutorService;

/**
 * Created by shawxy on 7/28/16.
 */
public interface CrawlerHive {

    void goCraw();

    void setConfig(HiveConfig config);

}
