/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.ApplicationException;

/**
 *
 * @author zua
 */
@ApplicationException
public class DBException extends RuntimeException {

    public DBException(String message) {
        super(message);
    }

    
}
