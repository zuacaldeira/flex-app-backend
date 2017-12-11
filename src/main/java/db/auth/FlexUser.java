/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.auth;

import db.GraphEntity;
import db.opinion.Fake;
import db.opinion.Favorite;
import db.opinion.Read;
import java.util.HashSet;
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
    private Set<Read> read;

    /**
     * Articles that user finds relevant.
     */
    @Relationship(type = "FAVORITE")
    private Set<Favorite> favorite;

    /**
     * Articles that user finds abusive for any reason.
     */
    @Relationship(type = "FAKE")
    private Set<Fake> fake;

    /**
     * User authorization object.
     */
    @Relationship(type = "AUTHORIZED_AS")
    private AuthUserInfo userInfo;

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

    /**
     * Returns articles the user do not want to see. This is a uncategorized
     * type of filter, that filters out selected articles from being retrieved
     * from database.
     *
     * @return Set of articles marked as Read
     */
    public Set<Read> getRead() {
        return read;
    }

    /**
     * Set the articles not to be retrieved from the database.
     *
     * @param read
     */
    public void setRead(Set<Read> read) {
        this.read = read;
    }

    /**
     * Return the articles user finds relevant.
     *
     * @return User's favorite or relevant articles
     */
    public Set<Favorite> getFavorite() {
        return favorite;
    }

    /**
     * Set relevant or favorite articles.
     *
     * @param favorite Favorite or relevant articles
     */
    public void setFavorite(Set<Favorite> favorite) {
        this.favorite = favorite;
    }

    /**
     * Return the articles user finds abusive.
     *
     * @return Abusive articles, as reported by this user
     */
    public Set<Fake> getFake() {
        return fake;
    }

    /**
     * Updates articles the user finds abusive.
     *
     * @param fake Abusive articles
     */
    public void setFake(Set<Fake> fake) {
        this.fake = fake;
    }

}
