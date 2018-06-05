/**
 * Interactive Novels: SecurityUtils.java
 */
package es.uca.inovels.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import es.uca.inovels.model.User;

/**
 * @author Francisco Fernández Sobejano
 *
 */
public class SecurityUtils {
	private SecurityUtils() {
    }

    public static boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    public static boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority(role));
    }
    
    public static User getUser(){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if(authentication != null ){
        	return (User) authentication.getPrincipal();
        } else{
        	return null;
        }
    }
    
    public static String getUsername(){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if(authentication != null ){
        	return authentication.getName();
        } else{
        	return null;
        }
    }
    
    public static Collection<? extends GrantedAuthority> roles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if(authentication != null ){
        	return authentication.getAuthorities();
        } else{
        	return null;
        }
    }
}
