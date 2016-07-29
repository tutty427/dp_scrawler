package org.test.org.test;

import org.junit.Test;
import org.shaw.go.scrawler.base.HiveFactory;
import org.shaw.go.scrawler.bean.HiveConfig;
import org.shaw.go.scrawler.main.DPCrawlerHive;

/**
 * Created by shawxy on 7/28/16.
 */
public class DPScrawlerTest {

    @Test
    public void doTest(){


        HiveConfig config =  new HiveConfig();
        config.setClazz(DPCrawlerHive.class);
        config.setCount(5);

        HiveFactory.create(config).goCraw();

    }

}
