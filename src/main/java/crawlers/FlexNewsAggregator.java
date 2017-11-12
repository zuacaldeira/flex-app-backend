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
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Schedule;

/**
 *
 * @author zua
 */
@Singleton
@Lock(LockType.READ)
public class FlexNewsAggregator {

    @EJB
    private ANacaoCVCrawler aNacaoCVCrawler;

    @EJB
    private AVerdadeOnlineCrawler aVerdadeOnlineCrawler;

    @EJB
    private IOLNewsIsolezweCrawler iOLNewsIsolezweCrawler;

    @EJB
    private IOLNewsZACrawler iOLNewsZACrawler;

    @EJB
    private JornalDeAngolaCrawler jornalDeAngolaCrawler;

    @EJB
    private MakaAngolaCrawler makaAngolaCrawler;

    @EJB
    private TelaNonCrawler telaNonCrawler;

    @EJB
    private TheBugleZACrawler theBugleZACrawler;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Schedule(hour = "*", minute = "0/30")
    public void crawlANacaoCV() {
        try {
            // Our crawlers and aggregators of crawlers 
            aNacaoCVCrawler.crawl();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Schedule(hour = "*", minute = "1/30")
    public void crawlAVerdadeOnline() {
        try {
            aVerdadeOnlineCrawler.crawl();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Schedule(hour = "*", minute = "2/30")
    public void crawlIOLNews() {
        try {
            // Our crawlers and aggregators of crawlers 
            iOLNewsIsolezweCrawler.crawl();
            iOLNewsZACrawler.crawl();
            //new DBCompletionCrawler().crawl();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Schedule(hour = "*", minute = "3/30")
    public void crawlJornalDeAngola() {
        try {
            // Our crawlers and aggregators of crawlers 
            jornalDeAngolaCrawler.crawl();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Schedule(hour = "*", minute = "4/30")
    public void crawlMakaAngola() {
        try {
            // Our crawlers and aggregators of crawlers 
            makaAngolaCrawler.crawl();
            //new DBCompletionCrawler().crawl();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Schedule(hour = "*", minute = "5/30")
    public void crawlTelaNon() {
        try {
            telaNonCrawler.crawl();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Schedule(hour = "*", minute = "6/30")
    public void crawlTheBuggle() {
        try {
            theBugleZACrawler.crawl();
            //new DBCompletionCrawler().crawl();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
