/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.AmazonProperties;
import db.Neo4jSessionFactory;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;

/**
 *
 * @author zua
 */
@Stateless
public class FlexAmazonService implements FlexAmazonServiceInterface {

    @Override
    public String createSignedRequest(String searchIndex, String keywords) {
        String cypher = "MATCH (n:AmazonProperties) RETURN n";
        AmazonProperties properties = Neo4jSessionFactory.getInstance().getNeo4jSession().queryForObject(AmazonProperties.class, cypher, new HashMap<>());
        Map<String, String> params = getParams(searchIndex, keywords, properties);
        String requestUrl = new SignedRequestsHelper(properties).sign(params);
        System.out.println("Signed URL: \"" + requestUrl + "\"");
        return requestUrl;
    }

    private Map<String, String> getParams(String searchIndex, String keywords, AmazonProperties properties) {
        Map<String, String> params = new HashMap<>();
        params.put("Service", "AWSECommerceService");
        params.put("Operation", "ItemSearch");
        params.put("AWSAccessKeyId", properties.getAccessKey());
        params.put("AssociateTag", properties.getAssociateTag());
        params.put("SearchIndex", searchIndex);
        params.put("Keywords", keywords);
        params.put("ResponseGroup", "Images,ItemAttributes,Offers");
        return params;
    }

}
