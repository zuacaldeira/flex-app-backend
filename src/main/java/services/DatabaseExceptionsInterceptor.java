/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author zua
 */
@Interceptor
public class DatabaseExceptionsInterceptor {

    private static final Logger LOG = Logger.getLogger(DatabaseExceptionsInterceptor.class.getName());

    @AroundInvoke
    public Object interceptCalls(InvocationContext ctx) throws Exception {
        LOG.log(Level.INFO, "Using interceptor: {0}", getClass().getSimpleName());
        try {
            return ctx.proceed();
        } catch(DBException e) {
            LOG.log(Level.WARNING, "Using interceptor: {0}", getClass().getSimpleName());
            return null;
        }
    }

}
