/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import db.NewsSource;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import services.NewsSourceServiceInterface;
import utils.FlexLogger;

/**
 *
 * @author zua
 */
@Singleton
public class DBCompletionCrawler {
    
    private FlexLogger logger = new FlexLogger(DBCompletionCrawler.class);
        
    @EJB
    private NewsSourceServiceInterface sourcesService;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Schedule(hour = "*", minute="1/10")
    public void completeLogos() {
        List<NewsSource> sources = (List<NewsSource>) sourcesService.findAll("logoUrl", null);
        logger.info("FFFFFFFFFF Found %d sources without logo", sources.size());
        
        for(NewsSource s: sources) {
            if(s.getLogoUrl() == null || s.getLogoUrl().isEmpty()) {
                logger.info("FFFFFFFFFF Found %s ", s);
                sourcesService.save(s);
            }
        }
    }
}
