package org.shaw.go.scrawler.base;

import org.jsoup.nodes.Document;
import org.shaw.go.scrawler.bean.ScrawlerResult;
import org.shaw.go.scrawler.bean.CallContext;



/**
 * Created by shawxy on 7/27/16.
 */
public abstract class AbstractScrawler<T,R> implements Scrawler{

    protected CallContext<R> callContext;


    public void setContext(CallContext context){
       this.callContext = context;
    }

    protected abstract Document call(R req);


    protected abstract R processCallRequest(R req);

    protected abstract ScrawlerResult<T> doParse( Document doc,R req);


    protected abstract Boolean procesResult( ScrawlerResult<T> result);


    public void doScraw(){

        for(;;){

            R req = callContext.getReq();
            Document doc = call(req);
            if(doc == null){
                callContext.setReq(processCallRequest(req));
                continue;
            }

            Boolean isStop = procesResult(doParse(doc,req));
            if(!isStop){
                break;
            }

            processCallRequest(req);

        }


    }


}
