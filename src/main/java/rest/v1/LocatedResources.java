/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.Located;
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
import services.news.LocatedService;

/**
 *
 * @author zua
 */
@Path("located")
public class LocatedResources {

    /**
     * UriInfo.
     */
    private UriInfo uriInfo;

    /**
     * Request.
     */
    private Request request;

    /**
     * News Located Service.
     */
    @EJB
    private LocatedService locatedService;

    /**
     * Instantiates a new instance of this resource type.
     */
    public LocatedResources() {
    }

    /**
     * Count the number of located.
     *
     * @return the number of distinct publishers.
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        long size = locatedService.count();
        return String.valueOf(size);
    }

    /**
     * Retrieves all news sources (publishers) stored in our system.
     *
     * @return a full list of publishers.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Located> getAll() {
        List<Located> result = new LinkedList<>();
        result.addAll(locatedService.findAll(0, 100));
        return result;
    }

    /**
     * Retrieves a single located resource object.
     *
     * @param id of the resource we are looking for.
     * @return a new {@code LocatedResource}.
     */
    @GET
    @Path("{id}")
    public LocatedResource getLocated(@PathParam("id") String id) {
        return new LocatedResource(uriInfo, request, id);
    }

}
