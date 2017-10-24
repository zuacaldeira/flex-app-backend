/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers.telaNon;

import crawlers.AbstractCrawlerTestIT;
import crawlers.FlexNewsCrawler;
import crawlers.telaNon.TelaNonCrawler;

/**
 *
 * @author zua
 */
public class TelaNonCrawlerTestIT extends AbstractCrawlerTestIT {

    public TelaNonCrawlerTestIT() {
    }

    @Override
    protected FlexNewsCrawler getCrawler() {
        return new TelaNonCrawler();
    }
}
