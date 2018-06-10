/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.LocaleInfo;
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
import services.news.LocaleInfoService;

/**
 *
 * @author zua
 */
public class LocaleInfoResource {
    
    /**
     * Private logger object.
     */
    private static final Logger logger = Logger.getLogger(LocaleInfoResource.class.getSimpleName());

    private final UriInfo uriInfo;
    private final Request request;
    private final String localeString;

    /**
     * News Source Service.
     */
    @EJB
    private LocaleInfoService localeInfoService;

    public LocaleInfoResource(UriInfo uriInfo, Request request, String localeString) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.localeString = localeString;
        this.localeInfoService = new LocaleInfoService();
    }

    /**
     * Get the tag associated with this resource.
     *
     * @return the associated tag domain model object.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public LocaleInfo getLocaleInfo() {
        return localeInfoService.find(localeString);
    }

    /**
     * Creates a new Tag object.
     *
     * @param localeInfoElement an element with the representation of the data
     * needed to create a tag object.
     * @return a response with the result of this operation.
     */
    @PUT
    @Produces(MediaType.APPLICATION_XML)
    public Response putTag(JAXBElement<LocaleInfo> localeInfoElement) {
        LocaleInfo localeInfo = localeInfoElement.getValue();
        Response response = null;

        if (localeInfoService.find(localeInfo.getLocaleString()) != null) {
            response = Response.noContent().build();
        } else {
            response = Response.created(uriInfo.getAbsolutePath()).build();
            localeInfoService.save(localeInfo);
        }

        return response;
    }

    /**
     * Deletes the tag object associated with this resource.
     */
    @DELETE
    public void deleteTag() {
        localeInfoService.delete(localeString);
    }

}
