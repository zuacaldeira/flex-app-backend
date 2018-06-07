/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.NewsSource;
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
import services.news.NewsSourceService;

/**
 *
 * @author zua
 */
@Path("sources")
public class NewsSourcesResource {

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
    private NewsSourceService newsSourceService;

    /**
     * Instantiates a new instance of this resource type.
     */
    public NewsSourcesResource() {
    }

    /**
     * Count the number of news sources (publishers).
     *
     * @return the number of distinct publishers.
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        long size = newsSourceService.count();
        return String.valueOf(size);
    }

    /**
     * Retrieves all news sources (publishers) stored in our system.
     *
     * @return a full list of publishers.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsSource> getAll() {
        List<NewsSource> result = new LinkedList<>();
        result.addAll(newsSourceService.findAllSources());
        return result;
    }

    /**
     * Retrieves a single news source resource object.
     *
     * @param sourceId of the resource we are looking for.
     * @return a new {@code NewsSourceResource}.
     */
    @GET
    @Path("{sourceId}")
    public NewsSourceResource getNewsSource(@PathParam("sourceId") String sourceId) {
        return new NewsSourceResource(uriInfo, request, sourceId);
    }

}
