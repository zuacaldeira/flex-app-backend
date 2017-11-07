/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers.aVerdadeOnline;

import crawlers.AbstractCrawlerTestIT;
import crawlers.FlexNewsCrawler;
import crawlers.exceptions.ImageNotFoundException;
import org.junit.Test;

/**
 *
 * @author zua
 */
public class AVerdadeOnlineCrawlerTestIT extends AbstractCrawlerTestIT {

    public AVerdadeOnlineCrawlerTestIT() {
    }

    @Override
    protected FlexNewsCrawler getCrawler() {
        return new AVerdadeOnlineCrawler();
    }

    @Override
    @Test(expected=ImageNotFoundException.class)
    public void testImportArticle() {
        super.testImportArticle();
    }
    
    
}
