/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.Publish;
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
import services.news.PublishService;

/**
 *
 * @author zua
 */
public class PublishResource {
    
    /**
     * Private logger object.
     */
    private static final Logger LOGGER = Logger.getLogger(PublishResource.class.getSimpleName());

    private final UriInfo uriInfo;
    private final Request request;
    private final String id;

    /**
     * News Source Service.
     */
    @EJB
    private final PublishService publishesService;

    public PublishResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
        this.publishesService = new PublishService();
    }

    /**
     * Get the tag associated with this resource.
     *
     * @return the associated tag domain model object.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Publish getPublishes() {
        return publishesService.findByIndex(id);
    }

    /**
     * Creates a new Publish object.
     *
     * @param tagElement an element with the representation of the data
     * needed to create a tag object.
     * @return a response with the result of this operation.
     */
    @PUT
    @Produces(MediaType.APPLICATION_XML)
    public Response putPublishes(JAXBElement<Publish> tagElement) {
        Publish aPublishes = tagElement.getValue();
        Response response = null;

        if (publishesService.find(aPublishes.getId()) != null) {
            response = Response.noContent().build();
        } else {
            response = Response.created(uriInfo.getAbsolutePath()).build();
            publishesService.save(aPublishes);
        }

        return response;
    }

    /**
     * Deletes the tag object associated with this resource.
     */
    @DELETE
    public void deletePublishes() {
        publishesService.delete(id);
    }

}
