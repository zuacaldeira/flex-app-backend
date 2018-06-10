/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.NewsArticle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import services.news.NewsArticleService;

/**
 *
 * @author zua
 */
public class NewsArticleResource {
    
    /**
     * Private logger object.
     */
    private static final Logger logger = Logger.getLogger(NewsArticleResource.class.getSimpleName());

    private final UriInfo uriInfo;
    private final Request request;
    private final String articleId;

    /**
     * News Article Service.
     */
    @EJB
    private NewsArticleService newsArticleService;

    public NewsArticleResource(UriInfo uriInfo, Request request, String sourceId) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.articleId = sourceId;
        this.newsArticleService = new NewsArticleService();
    }

    /**
     * Get the news article associated with this resource.
     *
     * @return the associated news article domain model object.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public NewsArticle getNewsArticle() {
        return newsArticleService.find(articleId);
    }

    /**
     * Creates a new News Article object. If the object already exists, return a
     * response without content. Otherwise, returns a response with CREATED
     * status to signalize that the object was successfully created.
     *
     * @param newsArticleElement an element with the representation of the data
     * needed to create a news article (publisher) object.
     * @return a response with the result of this operation.
     */
    @PUT
    @Produces(MediaType.APPLICATION_XML)
    public Response putNewsArticle(JAXBElement<NewsArticle> newsArticleElement) {
        NewsArticle article = newsArticleElement.getValue();
        Response response = null;

        if (newsArticleService.find(articleId) != null) {
            response = Response.noContent().build();
        } else {
            response = Response.created(uriInfo.getAbsolutePath()).build();
            newsArticleService.save(article);
        }

        return response;
    }

    /**
     * Deletes the publisher object associated with this resource.
     */
    @DELETE
    public void deleteNewsArticle() {
        newsArticleService.delete(articleId);
    }

}
