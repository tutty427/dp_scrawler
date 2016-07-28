package org.test.org.test;

import org.junit.Test;
import org.shaw.go.scrawler.base.HiveFactory;

/**
 * Created by shawxy on 7/28/16.
 */
public class DPScrawlerTest {

    @Test
    public void doTest(){

        HiveFactory.create(null).goCraw();


    }

}
