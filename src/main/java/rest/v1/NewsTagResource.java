/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.v1;

import db.news.Tag;
import java.util.logging.Level;
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
import services.news.NewsTagService;

/**
 *
 * @author zua
 */
public class NewsTagResource {
    
    /**
     * Private logger object.
     */
    private static final Logger logger = Logger.getLogger(NewsTagResource.class.getSimpleName());

    private final UriInfo uriInfo;
    private final Request request;
    private final String tagname;

    /**
     * News Source Service.
     */
    @EJB
    private NewsTagService newsTagService;

    public NewsTagResource(UriInfo uriInfo, Request request, String tagname) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.tagname = tagname;
        this.newsTagService = new NewsTagService();
    }

    /**
     * Get the tag associated with this resource.
     *
     * @return the associated tag domain model object.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Tag getNewsTag() {
        logger.log(Level.INFO, "{0}#getNewsSource()", NewsTagResource.class.getSimpleName());
        logger.log(Level.INFO, "NewsSourceService available? {0}", String.valueOf(newsTagService != null));
        logger.log(Level.INFO, "Source Id available? {0}", String.valueOf(tagname != null));
        return newsTagService.find(tagname);
    }

    /**
     * Creates a new Tag object.
     *
     * @param tagElement an element with the representation of the data
     * needed to create a tag object.
     * @return a response with the result of this operation.
     */
    @PUT
    @Produces(MediaType.APPLICATION_XML)
    public Response putTag(JAXBElement<Tag> tagElement) {
        Tag aTag = tagElement.getValue();
        Response response = null;

        if (newsTagService.find(aTag.getTag()) != null) {
            response = Response.noContent().build();
        } else {
            response = Response.created(uriInfo.getAbsolutePath()).build();
            newsTagService.save(aTag);
        }

        return response;
    }

    /**
     * Deletes the tag object associated with this resource.
     */
    @DELETE
    public void deleteTag() {
        newsTagService.delete(tagname);
    }

}
