/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.Publish;
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
import services.news.PublishService;

/**
 *
 * @author zua
 */
@Path("publishes")
public class PublishesResource {

    /**
     * UriInfo.
     */
    private UriInfo uriInfo;

    /**
     * Request.
     */
    private Request request;

    /**
     * News Publish Service.
     */
    @EJB
    private PublishService publishesService;

    /**
     * Instantiates a new instance of this resource type.
     */
    public PublishesResource() {
    }

    /**
     * Count the number of publish relationships.
     *
     * @return the number of distinct publishers.
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        long size = publishesService.count();
        return String.valueOf(size);
    }

    /**
     * Retrieves all news sources (publishers) stored in our system.
     *
     * @return a full list of publishers.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Publish> getAll() {
        List<Publish> result = new LinkedList<>();
        result.addAll(publishesService.findAll(0, 100));
        return result;
    }

    /**
     * Retrieves a single publishes resource object.
     *
     * @param id of the resource we are looking for.
     * @return a new {@code NewsPublishesResource}.
     */
    @GET
    @Path("{publishId}")
    public PublishResource getNewsSource(@PathParam("publishId") String id) {
        return new PublishResource(uriInfo, request, id);
    }

}
