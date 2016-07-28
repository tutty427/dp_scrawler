package org.shaw.go.scrawler.bean;

import java.util.Date;

/**
 * Created by shawxy on 7/27/16.
 */
import lombok.Data;

@Data
public class ScrawlerResult<T> {

   private String sourceURL;
   private Date scrawTime;

   private T data;
}
