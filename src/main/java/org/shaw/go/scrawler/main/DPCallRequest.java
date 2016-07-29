package org.shaw.go.scrawler.main;

/**
 * Created by shawxy on 7/28/16.
 */
import lombok.Data;
import org.shaw.go.scrawler.base.CallRequest;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class DPCallRequest implements CallRequest {

    private AtomicInteger shopId;




    public void setShopId(int shopId){


        this.shopId = new AtomicInteger(shopId);
    }



    public int increaseShopId(int num){

        return shopId.addAndGet(num);

    }
}
