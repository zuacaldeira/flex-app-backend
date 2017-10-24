/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import crawlers.aNacaoCv.ANacaoCVCrawler;
import crawlers.aVerdadeOnline.AVerdadeOnlineCrawler;
import crawlers.dbCompletion.DBCompletionCrawler;
import crawlers.globalVoices.GlobalVoicesCrawler;
import crawlers.iolNews.IOLNewsIsolezweCrawler;
import crawlers.iolNews.IOLNewsZACrawler;
import crawlers.jornalDeAngola.JornalDeAngolaCrawler;
import crawlers.makaAngola.MakaAngolaCrawler;
import crawlers.newsApi.FlexObjectMapper;
import crawlers.telaNon.TelaNonCrawler;
import crawlers.theBugleZa.TheBugleZACrawler;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;

/**
 *
 * @author zua
 */
@Singleton
@LocalBean
public class CrawlerSingletonBean {

    @EJB
    private ANacaoCVCrawler aNacaoCVCrawler;

    @EJB
    private AVerdadeOnlineCrawler aVerdadeOnlineCrawler;

    @EJB
    private DBCompletionCrawler dBCompletionCrawler;

    @EJB
    private GlobalVoicesCrawler globalVoicesCrawler;

    @EJB
    private IOLNewsIsolezweCrawler iOLNewsIsolezweCrawler;

    @EJB
    private IOLNewsZACrawler iOLNewsZACrawler;

    @EJB
    private JornalDeAngolaCrawler jornalDeAngolaCrawler;

    @EJB
    private MakaAngolaCrawler makaAngolaCrawler;

    @EJB
    private FlexObjectMapper flexObjectMapper;

    @EJB
    private TelaNonCrawler telaNonCrawler;

    @EJB
    private TheBugleZACrawler theBugleZACrawler;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Schedule(hour = "*", minute = "*/15")    
    public void crawl() {
        aNacaoCVCrawler.crawl();
        aVerdadeOnlineCrawler.crawl();
        //globalVoicesCrawler.crawl();
        iOLNewsIsolezweCrawler.crawl();
        iOLNewsZACrawler.crawl();
        jornalDeAngolaCrawler.crawl();
        makaAngolaCrawler.crawl();
        flexObjectMapper.crawl();
        telaNonCrawler.crawl();
        theBugleZACrawler.crawl();
        dBCompletionCrawler.crawl();
    }
    
    
}
