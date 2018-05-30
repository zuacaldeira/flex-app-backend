/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v1.backend.services;

import javax.ejb.ApplicationException;

/**
 *
 * @author zua
 */
@ApplicationException
public class DBException extends RuntimeException {

    private static final long serialVersionUID = -260086311866408764L;

    public DBException(String message) {
        super(message);
    }

}
