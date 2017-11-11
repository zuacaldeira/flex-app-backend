/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.Person;
import javax.ejb.Remote;

/**
 *
 * @author zua
 */
@Remote
public interface FlexPeopleServiceInterface {
    public Person findPersonNamed(String name);
}
