/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers.dbCompletion;

import crawlers.Logos;
import db.NewsSource;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import services.NewsSourceService;
import services.NewsSourceServiceInterface;
import utils.FlexLogger;

/**
 *
 * @author zua
 */
@Singleton
@TransactionManagement(TransactionManagementType.CONTAINER)

public class DBCompletionCrawler {

    private final FlexLogger logger = new FlexLogger(DBCompletionCrawler.class);

    private NewsSourceServiceInterface sourcesService = new NewsSourceService();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Schedule(hour="*", minute="*/10")
    public void crawl() {
        try {
            List<NewsSource> sources = (List<NewsSource>) sourcesService.findAllSources();
            logger.info("FFFFFFFFFF Found %d sources", sources.size());

            for (NewsSource s : sources) {
                if (s.getLogoUrl() == null || s.getLogoUrl().isEmpty()) {
                    logger.info("FFFFFFFFFF Found %s without logo", s.getSourceId());
                    s.setLogoUrl(Logos.getLogo(s.getSourceId()));
                    try {
                        sourcesService.save(s);
                    } catch (Exception e) {
                        logger.error("Found Exception %s", e);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Found Exception %s", e);
        }
    }

    public void setSourcesService(NewsSourceServiceInterface sourcesService) {
        this.sourcesService = sourcesService;
    }

}
