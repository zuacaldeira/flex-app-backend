/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import com.sun.istack.internal.logging.Logger;
import db.news.NewsSource;
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
import services.news.NewsSourceService;

/**
 *
 * @author zua
 */
public class NewsSourceResource {
    
    /**
     * Private logger object.
     */
    private static final Logger logger = Logger.getLogger(NewsSourceResource.class);

    private final UriInfo uriInfo;
    private final Request request;
    private final String sourceId;

    /**
     * News Source Service.
     */
    @EJB
    private NewsSourceService newsSourceService;

    public NewsSourceResource(UriInfo uriInfo, Request request, String sourceId) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.sourceId = sourceId;
        this.newsSourceService = new NewsSourceService();
    }

    /**
     * Get the news source associated with this resource.
     *
     * @return the associated news source domain model object.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public NewsSource getNewsSource() {
        logger.info(NewsSourceResource.class.getSimpleName() + "#getNewsSource()");
        logger.info("NewsSourceService available? " + String.valueOf(newsSourceService != null));
        logger.info("Source Id available? " + String.valueOf(sourceId != null));
        return newsSourceService.findSourceWithSourceId(sourceId);
    }

    /**
     * Creates a new News Source object. If the object already exists, return a
     * response without content. Otherwise, returns a response with CREATED
     * status to signalize that the object was successfully created.
     *
     * @param newsSourceElement an element with the representation of the data
     * needed to create a news source (publisher) object.
     * @return a response with the result of this operation.
     */
    @PUT
    @Produces(MediaType.APPLICATION_XML)
    public Response putNewsSource(JAXBElement<NewsSource> newsSourceElement) {
        NewsSource source = newsSourceElement.getValue();
        Response response = null;

        if (newsSourceService.findSourceWithSourceId(sourceId) != null) {
            response = Response.noContent().build();
        } else {
            response = Response.created(uriInfo.getAbsolutePath()).build();
            newsSourceService.save(source);
        }

        return response;
    }

    /**
     * Deletes the publisher object associated with this resource.
     */
    @DELETE
    public void deleteNewsSource() {
        newsSourceService.delete(sourceId);
    }

}
