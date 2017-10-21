/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

/**
 *
 * @author zua
 */
public class IOLNewsZACrawlerSlowTest extends AbstractCrawlerTestIT {

    public IOLNewsZACrawlerSlowTest() {
    }

    @Override
    protected FlexNewsCrawler getCrawler() {
        return new IOLNewsZACrawler();
    }
}
