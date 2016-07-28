package org.shaw.go.scrawler.base;

import org.shaw.go.scrawler.bean.CallContext;
import org.shaw.go.scrawler.bean.DPCallRequest;
import org.shaw.go.scrawler.main.DPShopScrawler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by shawxy on 7/28/16.
 */
public class DPCrawlerHive extends BaseCrawlerHive {


    @Override
    public void goCraw() {

        List<Future<Boolean>> end = new ArrayList<Future<Boolean>>();

        for(int i = 0 ; i< getCrawlers(); i++){
            CallContext<DPCallRequest> callContext = new CallContext<DPCallRequest>();
            DPCallRequest req = new DPCallRequest();
            req.setShopId(500000);

            callContext.setReq(req);
            Scrawler scrawler = new DPShopScrawler();
            scrawler.setContext(callContext);
            end.add(getHome().submit(new AsyncJob(scrawler)));

        }


       Iterator it =  end.iterator();
        while(it.hasNext()){

            Future<Boolean> endFlag = (Future<Boolean>) it.next();
            try {
                endFlag.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

    }

}
