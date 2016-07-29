package org.shaw.go.scrawler.main;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shaw.go.scrawler.base.AbstractScrawler;
import org.shaw.go.scrawler.bean.ScrawlerResult;

import java.io.IOException;
import java.util.Date;


/**
 * Created by shawxy on 7/27/16.
 */
public class DPShopScrawler extends AbstractScrawler<DPShop,DPCallRequest> {


    private String URL = "http://www.dianping.com/shop/";


    private final static int max_id = 67000000;

    private volatile boolean await = false;

    @Override
    protected Document call(DPCallRequest req) {

        System.out.println(req);
        Document doc = null;
        try{
            String point = URL + req.getShopId();
            System.out.println(point);
            doc = Jsoup.connect(point)
                    .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:47.0) Gecko/20100101 Firefox/47.0")
                    .get();
        }catch (HttpStatusException e){
            System.out.println(req.getShopId());
        } catch (IOException e) {
           // e.printStackTrace();
        }

        return doc;
    }

    @Override
    protected DPCallRequest processCallRequest(DPCallRequest req, boolean needSpecial) {

        while(await){
            try {
                //Stragetgy is working, search for the validate id
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(!needSpecial){
            req.increaseShopId(1);
            return req;

        }


        if(needSpecial){
            await = true;
        }


        return req;

    }

    @Override
    protected ScrawlerResult<DPShop> doParse(Document doc , DPCallRequest req) {


        ScrawlerResult<DPShop> result = new ScrawlerResult<DPShop>();
        DPShop shop = new DPShop();


        shop.setShopId(String.valueOf(req.getShopId()));



        Elements shopName = doc.select("div#basic-info>h1.shop-name");
        for(Element e : shopName){
            shop.setShopName(e.ownText());  //shopName
        }

        Elements address = doc.select("div[itemprop=street-address]");
        shop.setShopName(address.text());    //address
       // System.out.println( address.text());

        Elements phones = doc.select("span[itemprop='tel']");
        StringBuilder phones_string = new StringBuilder();
        for(Element e : phones){
            phones_string.append(e.text()).append(" ");
        }
        shop.setPhone(phones_string.toString()); //phone

        Elements star = doc.select("span.mid-rank-stars");
        shop.setStar(star.attr("title"));    //星级

        Elements others = doc.select("div.J-other>p.info-indent");
        for(Element other : others){
            String key = other.select("p>span.info-name").text();
            // System.out.println(key);
            if(key.contains("营业时间")){
                String value = other.select("p>span.item").text();
                shop.setOpeningTime(value);
            }
            if(key.contains("餐厅简介")){
                String value = other.select("p").text();
                shop.setIntroduction(value);
            }

        }


        Elements scores = doc.select("div.brief-info>span.item");

        for(Element score : scores){
            String key = score.text();

            if(key.contains("人均")){
               // System.out.println(key);
                shop.setAvgPrice(key);
            }
            if(key.contains("口味")){
               // System.out.println(key);
                shop.setTasteScore(key);
            }
            if(key.contains("环境")){
               // System.out.println(key);
                shop.setEnvScore(key);
            }
            if(key.contains("服务")){
                //System.out.println(key);
                shop.setServiceScore(key);
            }

        }

        result.setScrawTime(new Date());
        result.setSourceURL(URL + req.getShopId());
        result.setData(shop);

        return result;
    }

    @Override
    protected Boolean procesResult(ScrawlerResult<DPShop> result) {


        System.out.println(result);

        if(result.getData() == null){
            return false;
        }

        if(Integer.parseInt(result.getData().getShopId()) > max_id){
            return false;
        }

        return true;
    }







    //研究中, 如何快速找出有效的shopID起点－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－
    private int findValidShopId(int currentId) throws Exception {
        int salt = 1000;
        int offset = 1;


        int loopCount = 0;

        for(;;){
                loopCount++;
                boolean reuslt = validateShopId(currentId,salt,loopCount*offset);
                if(!reuslt){
                    offset++;
                    continue;
                }

                if(reuslt){


                }


        }

    }




    private boolean validateShopId(int currentId,int salt ,int offset) throws Exception {


        DPCallRequest req = new DPCallRequest();
        int tryId = currentId+(salt*offset);
        if(tryId > max_id){
            tryId = max_id;
        }
        req.setShopId(tryId);

        Document doc = call(req);
        if(doc == null ){
            if(tryId == max_id) {
                throw new Exception("stop word");
            }
            return false;
        }
        return true;

    }

}
