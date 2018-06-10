/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.Located;
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
import services.news.LocatedService;

/**
 *
 * @author zua
 */
public class LocatedResource {
    
    /**
     * Private logger object.
     */
    private static final Logger LOGGER = Logger.getLogger(LocatedResource.class.getSimpleName());

    private final UriInfo uriInfo;
    private final Request request;
    private final String id;

    /**
     * News Source Service.
     */
    @EJB
    private final LocatedService locatedService;

    public LocatedResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
        this.locatedService = new LocatedService();
    }

    /**
     * Get the located associated with this resource.
     *
     * @return the associated located domain model object.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Located getLocated() {
        return locatedService.findByIndex(id);
    }

    /**
     * Creates a new Located object.
     *
     * @param locatedElement an element with the representation of the data
     * needed to create a located object.
     * @return a response with the result of this operation.
     */
    @PUT
    @Produces(MediaType.APPLICATION_XML)
    public Response putLocated(JAXBElement<Located> locatedElement) {
        Located aLocated = locatedElement.getValue();
        Response response = null;

        if (locatedService.find(aLocated.getId()) != null) {
            response = Response.noContent().build();
        } else {
            response = Response.created(uriInfo.getAbsolutePath()).build();
            locatedService.save(aLocated);
        }

        return response;
    }

    /**
     * Deletes the located object associated with this resource.
     */
    @DELETE
    public void deleteLocated() {
        locatedService.delete(id);
    }

}
