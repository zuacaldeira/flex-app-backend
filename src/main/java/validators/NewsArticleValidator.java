/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import db.news.NewsArticle;

/**
 *
 * @author zua
 */
public class NewsArticleValidator extends GraphEntityValidator<NewsArticle> {

    @Override
    public boolean isValid(NewsArticle entity) {
        return entity.getTitle() != null
                && entity.getUrl() != null;
    }
    
}
