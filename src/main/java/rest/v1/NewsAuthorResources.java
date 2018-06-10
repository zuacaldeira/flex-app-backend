/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.NewsAuthor;
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
import services.news.NewsAuthorService;

/**
 *
 * @author zua
 */
@Path("authors")
public class NewsAuthorResources {

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
    private final NewsAuthorService newsAuthorService;

    /**
     * Instantiates a new instance of this resource type.
     */
    public NewsAuthorResources() {
        newsAuthorService = new NewsAuthorService();
    }

    /**
     * Count the number of new authors.
     *
     * @return the number of distinct publishers.
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        long size = newsAuthorService.count();
        return String.valueOf(size);
    }

    /**
     * Retrieves all new authors stored in our system.
     *
     * @return a full list of publishers.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsAuthor> getAll() {
        List<NewsAuthor> result = new LinkedList<>();
        result.addAll(newsAuthorService.findAll(0, 100));
        return result;
    }

    /**
     * Retrieves a single new author resource object.
     *
     * @param name of the resource we are looking for.
     * @return a new {@code NewsAuthorResource}.
     */
    @GET
    @Path("{name}")
    public NewsAuthorResource getNewsAuthor(@PathParam("name") String name) {
        return new NewsAuthorResource(uriInfo, request, name);
    }

}
