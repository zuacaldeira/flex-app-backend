/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers.dbCompletion;

import crawlers.Logos;
import db.NewsSource;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import services.NewsSourceServiceInterface;
import utils.FlexLogger;

/**
 *
 * @author zua
 */
@Stateless
public class DBCompletionCrawler {

    private FlexLogger logger = new FlexLogger(DBCompletionCrawler.class);

    @EJB
    private NewsSourceServiceInterface sourcesService;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void crawl() {
        List<NewsSource> sources = (List<NewsSource>) sourcesService.findAllSources();
        logger.info("FFFFFFFFFF Found %d sources", sources.size());

        for (NewsSource s : sources) {
            if (s.getLogoUrl() == null || s.getLogoUrl().isEmpty()) {
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
