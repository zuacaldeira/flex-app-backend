/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.NewsAuthor;
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
import services.news.NewsAuthorService;

/**
 *
 * @author zua
 */
public class NewsAuthorResource {
    
    /**
     * Private logger object.
     */
    private static final Logger LOGGER = Logger.getLogger(NewsAuthorResource.class.getSimpleName());

    private final UriInfo uriInfo;
    private final Request request;
    private final String authorId;

    /**
     * News Author Service.
     */
    @EJB
    private final NewsAuthorService newsAuthorService;

    public NewsAuthorResource(UriInfo uriInfo, Request request, String authorId) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.authorId = authorId;
        this.newsAuthorService = new NewsAuthorService();
    }

    /**
     * Get the news author associated with this resource.
     *
     * @return the associated news author domain model object.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public NewsAuthor getNewsAuthor() {
        return newsAuthorService.find(authorId);
    }

    /**
     * Creates a new News Author object. If the object already exists, return a
     * response without content. Otherwise, returns a response with CREATED
     * status to signalize that the object was successfully created.
     *
     * @param newsAuthorElement an element with the representation of the data
     * needed to create a news author (publisher) object.
     * @return a response with the result of this operation.
     */
    @PUT
    @Produces(MediaType.APPLICATION_XML)
    public Response putNewsAuthor(JAXBElement<NewsAuthor> newsAuthorElement) {
        NewsAuthor author = newsAuthorElement.getValue();
        Response response = null;

        if (newsAuthorService.find(authorId) != null) {
            response = Response.noContent().build();
        } else {
            response = Response.created(uriInfo.getAbsolutePath()).build();
            newsAuthorService.save(author);
        }

        return response;
    }

    /**
     * Deletes the publisher object associated with this resource.
     */
    @DELETE
    public void deleteNewsAuthor() {
        newsAuthorService.delete(authorId);
    }

}
