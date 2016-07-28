package org.shaw.go.scrawler.bean;

/**
 * Created by shawxy on 7/27/16.
 */
import lombok.Data;

@Data
public class DPShop {

    //商户ID
    private String shopId;
    //商户名
    private String shopName;
    //人均
    private String avgPrice;
    //口味，环境 ，服务评分
    private String tasteScore;
    private String envScore;
    private String serviceScore;
    //商户地址
    private String address;

    //分类 DETAIL 页面似乎抓不了 有待研究
    private String type;

    //推荐菜 有待研究
    private String recommondMeun;

    //营业时间
    private String openingTime;
    //电话
    private String phone;
    //星级
    private String star;
    //介绍
    private String introduction;
}
