/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.Writes;
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
import services.news.WriteService;

/**
 *
 * @author zua
 */
@Path("writes")
public class WritesResource {

    /**
     * UriInfo.
     */
    private UriInfo uriInfo;

    /**
     * Request.
     */
    private Request request;

    /**
     * News Writes Service.
     */
    @EJB
    private WriteService writesService;

    /**
     * Instantiates a new instance of this resource type.
     */
    public WritesResource() {
    }

    /**
     * Count the number of writess.
     *
     * @return the number of distinct publishers.
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        long size = writesService.count();
        return String.valueOf(size);
    }

    /**
     * Retrieves all news sources (publishers) stored in our system.
     *
     * @return a full list of publishers.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Writes> getAll() {
        List<Writes> result = new LinkedList<>();
        result.addAll(writesService.findAll(0, 100));
        return result;
    }

    /**
     * Retrieves a single writes resource object.
     *
     * @param writesId of the resource we are looking for.
     * @return a new {@code NewsWritesResource}.
     */
    @GET
    @Path("{writesId}")
    public WriteResource getNewsSource(@PathParam("writesId") String writesId) {
        return new WriteResource(uriInfo, request, writesId);
    }

}
