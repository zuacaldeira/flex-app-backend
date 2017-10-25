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
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Schedule;
import org.apache.commons.lang3.RandomUtils;

/**
 *
 * @author zua
 */
@Singleton
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
    
    private List<FlexNewsCrawler> crawlersList;
    
    @PostConstruct
    public void initialize() {
        crawlersList = new LinkedList<>();
        crawlersList.add(aNacaoCVCrawler);
        crawlersList.add(aVerdadeOnlineCrawler);
        crawlersList.add(globalVoicesCrawler);
        crawlersList.add(iOLNewsIsolezweCrawler);
        crawlersList.add(iOLNewsZACrawler);
        crawlersList.add(jornalDeAngolaCrawler);
        crawlersList.add(makaAngolaCrawler);
        crawlersList.add(telaNonCrawler);
        crawlersList.add(theBugleZACrawler);        
    }

    @Schedule(hour = "*", minute = "*/5")    
    public void crawl() {
        int i = RandomUtils.nextInt(0, crawlersList.size());
        crawlersList.get(i).crawl();
    }
    
    
}
