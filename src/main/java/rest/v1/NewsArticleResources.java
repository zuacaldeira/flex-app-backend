/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.NewsArticle;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import services.news.NewsArticleService;

/**
 *
 * @author zua
 */
@Path("articles")
public class NewsArticleResources {

    /**
     * UriInfo.
     */
    private UriInfo uriInfo;

    /**
     * Request.
     */
    private Request request;

    /**
     * News Source Service.
     */
    @EJB
    private NewsArticleService newsArticleService;

    /**
     * Instantiates a new instance of this resource type.
     */
    public NewsArticleResources() {
    }

    /**
     * Count the number of new articles (publishers).
     *
     * @return the number of distinct publishers.
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        long size = newsArticleService.count();
        return String.valueOf(size);
    }

    /**
     * Retrieves all new articles (publishers) stored in our system.
     *
     * @return a full list of publishers.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsArticle> getAll() {
        List<NewsArticle> result = new LinkedList<>();
        result.addAll(newsArticleService.findAll(0, 50));
        return result;
    }

    /**
     * Retrieves a single new article resource object.
     *
     * @param articleId of the resource we are looking for.
     * @return a new {@code NewsArticleResource}.
     */
    @GET
    @Path("{articleId}")
    public NewsArticleResource getNewsArticle(@PathParam("articleId") String articleId) {
        return new NewsArticleResource(uriInfo, request, articleId);
    }
    
}
