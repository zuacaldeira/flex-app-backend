/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import db.NewsSource;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.interceptor.Interceptors;
import services.DatabaseExceptionsInterceptor;
import services.NewsSourceServiceInterface;
import utils.FlexLogger;

/**
 *
 * @author zua
 */
@Singleton
@Interceptors(DatabaseExceptionsInterceptor.class)
public class DBCompletionCrawler {
    
    private FlexLogger logger = new FlexLogger(DBCompletionCrawler.class);
        
    @EJB
    private NewsSourceServiceInterface sourcesService;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Schedule(hour = "*", minute = "*/20", persistent = false)
    @Asynchronous
    public void crawl() {
        List<NewsSource> sources = (List<NewsSource>) sourcesService.findAllSources();
        logger.info("FFFFFFFFFF Found %d sources", sources.size());
        
        for(NewsSource s: sources) {
            if(s.getLogoUrl() == null || s.getLogoUrl().isEmpty()) {
                logger.info("FFFFFFFFFF Found %s without logo", s.getSourceId());
                s.setLogoUrl(Logos.getLogo(s.getSourceId()));
                sourcesService.save(s);
            }
        }
    }

    public void setSourcesService(NewsSourceServiceInterface sourcesService) {
        this.sourcesService = sourcesService;
    }
    
    
    
}
