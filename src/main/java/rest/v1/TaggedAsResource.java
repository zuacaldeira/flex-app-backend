/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.TaggedAs;
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
import services.news.TaggedAsService;

/**
 *
 * @author zua
 */
public class TaggedAsResource {
    
    /**
     * Private logger object.
     */
    private static final Logger LOGGER = Logger.getLogger(TaggedAsResource.class.getSimpleName());

    private final UriInfo uriInfo;
    private final Request request;
    private final String id;

    /**
     * News Source Service.
     */
    @EJB
    private final TaggedAsService taggedAsService;

    public TaggedAsResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
        this.taggedAsService = new TaggedAsService();
    }

    /**
     * Get the taggedAs associated with this resource.
     *
     * @return the associated taggedAs domain model object.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TaggedAs getTaggedAs() {
        return taggedAsService.findByIndex(id);
    }

    /**
     * Creates a new TaggedAs object.
     *
     * @param taggedAsElement an element with the representation of the data
     * needed to create a taggedAs object.
     * @return a response with the result of this operation.
     */
    @PUT
    @Produces(MediaType.APPLICATION_XML)
    public Response putTaggedAs(JAXBElement<TaggedAs> taggedAsElement) {
        TaggedAs aTaggedAs = taggedAsElement.getValue();
        Response response = null;

        if (taggedAsService.find(aTaggedAs.getId()) != null) {
            response = Response.noContent().build();
        } else {
            response = Response.created(uriInfo.getAbsolutePath()).build();
            taggedAsService.save(aTaggedAs);
        }

        return response;
    }

    /**
     * Deletes the taggedAs object associated with this resource.
     */
    @DELETE
    public void deleteTaggedAs() {
        taggedAsService.delete(id);
    }

}
