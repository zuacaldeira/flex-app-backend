/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.LocaleInfo;
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
import services.news.LocaleInfoService;

/**
 *
 * @author zua
 */
@Path("locales")
public class LocaleInfosResource {

    /**
     * UriInfo.
     */
    private UriInfo uriInfo;

    /**
     * Request.
     */
    private Request request;

    /**
     * News Tag Service.
     */
    @EJB
    private LocaleInfoService localeInfoResource;

    /**
     * Instantiates a new instance of this resource type.
     */
    public LocaleInfosResource() {
    }

    /**
     * Count the number of tags.
     *
     * @return the number of distinct publishers.
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        long size = localeInfoResource.count();
        return String.valueOf(size);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LocaleInfo> getAll() {
        List<LocaleInfo> result = new LinkedList<>();
        result.addAll(localeInfoResource.findAll());
        return result;
    }

    @GET
    @Path("{localeString}")
    public LocaleInfoResource getLocaleInfo(@PathParam("localeString") String localeString) {
        return new LocaleInfoResource(uriInfo, request, localeString);
    }

}
