/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import db.news.LocaleInfo;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.SortOrder;
import services.AbstractDBService;

/**
 *
 * @author zua
 */
@Stateless
public class LocaleInfoService extends AbstractDBService<LocaleInfo> {

    @Override
    public Class<LocaleInfo> getClassType() {
        return LocaleInfo.class;
    }

    @Override
    public SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC, "languageCode");
    }

    @Override
    public SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC, "languageCode");
    }
}
