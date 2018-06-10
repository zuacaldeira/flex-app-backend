/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.Writes;
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
import services.news.WriteService;

/**
 *
 * @author zua
 */
public class WriteResource {
    
    /**
     * Private logger object.
     */
    private static final Logger LOGGER = Logger.getLogger(WriteResource.class.getSimpleName());

    private final UriInfo uriInfo;
    private final Request request;
    private final String id;

    /**
     * News Source Service.
     */
    @EJB
    private final WriteService writesService;

    public WriteResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
        this.writesService = new WriteService();
    }

    /**
     * Get the write associated with this resource.
     *
     * @return the associated write domain model object.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Writes getWrites() {
        return writesService.findByIndex(id);
    }

    /**
     * Creates a new Writes object.
     *
     * @param writeElement an element with the representation of the data
     * needed to create a write object.
     * @return a response with the result of this operation.
     */
    @PUT
    @Produces(MediaType.APPLICATION_XML)
    public Response putWrites(JAXBElement<Writes> writeElement) {
        Writes aWrites = writeElement.getValue();
        Response response = null;

        if (writesService.find(aWrites.getId()) != null) {
            response = Response.noContent().build();
        } else {
            response = Response.created(uriInfo.getAbsolutePath()).build();
            writesService.save(aWrites);
        }

        return response;
    }

    /**
     * Deletes the write object associated with this resource.
     */
    @DELETE
    public void deleteWrites() {
        writesService.delete(id);
    }

}
