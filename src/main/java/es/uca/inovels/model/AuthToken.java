/**
 * Interactive Novels: AuthToken.java
 */
package es.uca.inovels.model;

/**
 * @author Francisco Fernández Sobejano
 *
 */
public class AuthToken {

    private String token;

    public AuthToken(){

    }

    public AuthToken(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}