/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.crawlers;

import java.util.HashSet;
import java.util.Set;
import utils.BackendUtils;

/**
 *
 * @author zua
 */
public class AuthorsElement extends ArticleElement {

    public AuthorsElement(String value) {
        super(value);
    }

    public Set<String> getAuthors() {
        if(getValue() != null) {
            return BackendUtils.getInstance().extractAuthorsNames(getValue());
        }
        return new HashSet<>();
    }
    
}
