/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import crawlers.aNacaoCv.ANacaoCVCrawler;
import crawlers.aVerdadeOnline.AVerdadeOnlineCrawler;
import crawlers.iolNews.IOLNewsIsolezweCrawler;
import crawlers.iolNews.IOLNewsZACrawler;
import crawlers.jornalDeAngola.JornalDeAngolaCrawler;
import crawlers.makaAngola.MakaAngolaCrawler;
import crawlers.telaNon.TelaNonCrawler;
import crawlers.theBugleZa.TheBugleZACrawler;
import javax.ejb.Singleton;
import javax.ejb.Schedule;
import javax.ejb.Startup;

/**
 *
 * @author zua
 */
@Singleton
@Startup
public class FlexNewsAggregator {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Schedule(hour = "*", minute = "5/20")
    public void aggregate() {
        try {
            // Our crawlers and aggregators of crawlers 
            new ANacaoCVCrawler().crawl();
            new AVerdadeOnlineCrawler().crawl();
            new IOLNewsIsolezweCrawler().crawl();
            new IOLNewsZACrawler().crawl();
            new JornalDeAngolaCrawler().crawl();
            new MakaAngolaCrawler().crawl();
            new TelaNonCrawler().crawl();
            new TheBugleZACrawler().crawl();
            //new DBCompletionCrawler().crawl();
        } catch(Throwable t) {}
    }
}
