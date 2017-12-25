/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.auth;

import db.GraphEntity;
import db.news.NewsArticle;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 *
 * @author zua
 */
@NodeEntity
public class FlexUser extends GraphEntity {

    private static final long serialVersionUID = -5822981670795508682L;

    /**
     * User unique internal identifier. It must be a verified email.
     */
    @Index(unique = true, primary = true)
    private String username;

    /**
     * User password.
     */
    private String password;

    /**
     * Articles that will not be presented to the user.
     */
    @Relationship(type = "READ")
    private Set<NewsArticle> read;

    /**
     * Articles that user finds relevant.
     */
    @Relationship(type = "FAVORITE")
    private Set<NewsArticle> favorite;

    /**
     * Articles that user finds abusive for any reason.
     */
    @Relationship(type = "FAKE")
    private Set<NewsArticle> fake;

    /**
     * User authorization object.
     */
    @Relationship(type = "AUTHORIZED_AS")
    private AuthUserInfo userInfo;
    
    /**
     * Checks whether this user is an administrator.
     */
    private boolean isAdmin;

    /**
     * Initialize an empty user.
     */
    public FlexUser() {
        read = new HashSet<>();
        favorite = new HashSet<>();
        fake = new HashSet<>();
    }

    /**
     * Initialize an object with the given credentials.
     *
     * @param username Username
     * @param password Password
     */
    public FlexUser(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    /**
     * User's usernane.
     *
     * @return Returns the user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Updates the user's username.
     *
     * @param username Username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * User's password. Must be used with care, since it cannot be transfered
     * over the wire. We are moving into a passwordless system, so this field is
     * temporary.
     *
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Updates user password.
     *
     * @param password New password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * The user authentication info.
     *
     * @return User authentication info
     */
    public AuthUserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * Updates user authentication info.
     *
     * @param userInfo
     */
    public void setUserInfo(AuthUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Set<NewsArticle> getRead() {
        return read;
    }

    public void setRead(Set<NewsArticle> read) {
        this.read = read;
    }

    public Set<NewsArticle> getFavorite() {
        return favorite;
    }

    public void setFavorite(Set<NewsArticle> favorite) {
        this.favorite = favorite;
    }

    public Set<NewsArticle> getFake() {
        return fake;
    }

    public void setFake(Set<NewsArticle> fake) {
        this.fake = fake;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.username);
        return hash;
    }

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
        final FlexUser other = (FlexUser) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }
}
