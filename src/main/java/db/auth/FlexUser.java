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
     * The role this user can authenticate in.
     */
    @Relationship(type = "HAS_ROLE")
    private UserRole role;

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
     * @param userInfo New user authentication info.
     */
    public void setUserInfo(AuthUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * Returns the articles the user marked as read. These articles will not be
     * presented to the user.
     *
     * @return The set of articles this user marked as {@code READ}.
     */
    public Set<NewsArticle> getRead() {
        return read;
    }

    /**
     * Updates the articles marked as {@code READ}.
     *
     * @param read New set of particles marked as {@code READ}.
     */
    public void setRead(Set<NewsArticle> read) {
        this.read = read;
    }

    /**
     * Returns the articles the user marked as favorite. These are the user's
     * recommendations.
     *
     * @return The set of articles this user marked as {@code FAVORITE}.
     */
    public Set<NewsArticle> getFavorite() {
        return favorite;
    }

    /**
     * Updates user's recommended or favorite articles.
     *
     * @param favorite New favorite article set.
     */
    public void setFavorite(Set<NewsArticle> favorite) {
        this.favorite = favorite;
    }

    /**
     * Returns the articles the user marked as abusive.
     *
     * @return The articles user finds abusive.
     */
    public Set<NewsArticle> getFake() {
        return fake;
    }

    /**
     * Updates the articles the user finds abusive.
     *
     * @param fake The new set of abusive articles.
     */
    public void setFake(Set<NewsArticle> fake) {
        this.fake = fake;
    }

    /**
     * The user role in the application.
     *
     * @return The user's role.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Updates the user role in the application.
     *
     * @param role The new user role.
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Return the object's hash code.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.username);
        return hash;
    }

    /**
     * Calculate whether two users are equal.
     *
     * @param obj The user object we are comparing with.
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
        final FlexUser other = (FlexUser) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether this user is an administrator. An administrator is a user
     * in relation with user role named {@literal Admin}.
     *
     * @return {@code true} if is an administrator, {@code false} otherwise.
     */
    public boolean isAdmin() {
        return role != null && role.equals(new UserRole("Admin"));
    }

}
