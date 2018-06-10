/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.TaggedAs;
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
import services.news.TaggedAsService;

/**
 *
 * @author zua
 */
@Path("taggedAs")
public class TaggedAsResources {

    /**
     * UriInfo.
     */
    private UriInfo uriInfo;

    /**
     * Request.
     */
    private Request request;

    /**
     * News TaggedAs Service.
     */
    @EJB
    private TaggedAsService taggedAsService;

    /**
     * Instantiates a new instance of this resource type.
     */
    public TaggedAsResources() {
    }

    /**
     * Count the number of taggedAs.
     *
     * @return the number of distinct publishers.
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        long size = taggedAsService.count();
        return String.valueOf(size);
    }

    /**
     * Retrieves all taggedAs objects stored in our system.
     *
     * @return a full list of publishers.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TaggedAs> getAll() {
        List<TaggedAs> result = new LinkedList<>();
        result.addAll(taggedAsService.findAll(0, 100));
        return result;
    }

    /**
     * Retrieves a single taggedAs resource object.
     *
     * @param id of the resource we are looking for.
     * @return a new {@code TaggedAsResource}.
     */
    @GET
    @Path("{id}")
    public TaggedAsResource getTaggedAs(@PathParam("id") String id) {
        return new TaggedAsResource(uriInfo, request, id);
    }

}
