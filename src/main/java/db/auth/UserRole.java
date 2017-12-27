/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.auth;

import db.GraphEntity;
import java.util.Objects;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author zua
 */
@NodeEntity
public class UserRole extends GraphEntity {

    private static final long serialVersionUID = -4495778748019754863L;

    /**
     * Role's name.
     */
    @Index(primary = true, unique = true)
    private String name;

    /**
     * Initializes an empty role
     */
    public UserRole() {
    }

    public UserRole(String roleName) {
        this.name = roleName;
    }

    /**
     * Returns the role's name.
     *
     * @return The role's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates role's name.
     *
     * @param name The new role's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the hash code of this object.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     * Compares this object for equality.
     *
     * @param obj The object we are comparing with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserRole other = (UserRole) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}
