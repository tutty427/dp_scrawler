package org.shaw.go.scrawler.bean;

/**
 * Created by shawxy on 7/28/16.
 */
import lombok.Data;
import org.shaw.go.scrawler.base.CallRequest;

@Data
public class DPCallRequest implements CallRequest {

    private int shopId;
}
