/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import db.GraphEntity;

/**
 *
 * @author zua
 * @param <T> A subtype of <code>GraphEntity</code>i
 */
public abstract class GraphEntityValidator<T extends GraphEntity> {
    public abstract boolean isValid(T entity);
}
