/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.Tag;
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
import services.news.NewsTagService;

/**
 *
 * @author zua
 */
@Path("tags")
public class NewsTagResources {

    /**
     * UriInfo.
     */
    private UriInfo uriInfo;

    /**
     * Request.
     */
    private Request request;

    /**
     * News Tag Service.
     */
    @EJB
    private NewsTagService newsTagService;

    /**
     * Instantiates a new instance of this resource type.
     */
    public NewsTagResources() {
    }

    /**
     * Count the number of tags.
     *
     * @return the number of distinct publishers.
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        long size = newsTagService.count();
        return String.valueOf(size);
    }

    /**
     * Retrieves all news sources (publishers) stored in our system.
     *
     * @return a full list of publishers.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> getAll() {
        List<Tag> result = new LinkedList<>();
        result.addAll(newsTagService.findAllTags());
        return result;
    }

    /**
     * Retrieves a single tag resource object.
     *
     * @param tag of the resource we are looking for.
     * @return a new {@code NewsTagResource}.
     */
    @GET
    @Path("{tag}")
    public NewsTagResource getNewsSource(@PathParam("tag") String tag) {
        return new NewsTagResource(uriInfo, request, tag);
    }

}
