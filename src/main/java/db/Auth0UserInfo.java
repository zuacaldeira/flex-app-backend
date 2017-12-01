/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.Date;
import java.util.Locale;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author zua
 */
@NodeEntity
public class Auth0UserInfo extends GraphEntity {

    private static final long serialVersionUID = 1359299010814564837L;

    @Index(primary = true, unique = true)
    private String sub;
    private boolean emailVerified;
    private Gender gender;
    private Date updatedAt;
    private String nickname;
    private String name;
    private String givenName;
    private Locale locale;
    private String familyName;
    private String picture;
    
    public Auth0UserInfo() {
    }

    @Override
    public String getPropertyName() {
        return "sub";
    }

    @Override
    public String getPropertyValue() {
        return sub;
    }

    @Override
    public boolean hasUrl() {
        return false;
    }

    @Override
    public String getUrl() {
        return null;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    
    
}
