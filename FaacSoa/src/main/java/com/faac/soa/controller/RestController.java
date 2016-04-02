package com.faac.soa.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * E' la classe che identifica l'interfaccia di comunicazione di tipo REST
 * che espone i servizi per poter effetturare le operazioni di base. 
 * @author Alessandro Poggi
 *
 */
@Controller
public class RestController {
	private static final Logger logger =LogManager.getLogger(RestController.class);

		@Autowired
	    private SessionRegistry sessionRegistry; 
		
	  /**
	   * Metodo che restituisce la pagina principale
	 * @return restituisce la pagina principale
	 */
		@RequestMapping("/")
	    public String getIndexPage() {
	        return "index";
	    }
	  
		/**
		 * Reindirizza alla pagina principale nel caso di percorsi di indirizzo non corretto
		 * @return restituisce la pagina principale
		 */
		@RequestMapping(value = "/{path:[^\\.]*}")
		public String redirect() {
			return "forward:/";
		}

		/**
		 * Servizio di recupero utenza login 
		 * @param user parametro che identifica l'utente in sicurezza
		 * @return restituisce l'id utente se l'autenticazione è andata a buon fine
		 */
		@RequestMapping("/user")
		@ResponseBody
		public Principal user(Principal user) {
			
			if(user!= null){
				UsernamePasswordAuthenticationToken  usr = (UsernamePasswordAuthenticationToken) user;
				WebAuthenticationDetails wad=(WebAuthenticationDetails) usr.getDetails();
				if(sessionRegistry.getSessionInformation(wad.getSessionId())==null){
					sessionRegistry.registerNewSession(wad.getSessionId(), user);
					logger.info("Login  user: "+user.getName()+", sessionid: "+wad.getSessionId() );
				}
				
			}
			return user;
		}

	    /** servizio di utenza logout
	     * @param request HttpServletRequest
	     * @param response HttpServletResponse
	     * @return restituisce una stringa di indirizzamento
	     * @see HttpServletRequest
	     * @see HttpServletResponse
	     */
	    @RequestMapping("/logout")
	    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    	
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if (auth != null){    
	        	WebAuthenticationDetails wad = (WebAuthenticationDetails) auth.getDetails();
	            new SecurityContextLogoutHandler().logout(request, response, auth);
	            sessionRegistry.removeSessionInformation(wad.getSessionId());
	            logger.info("logout  user: "+auth.getName()+", sessionid: "+wad.getSessionId() );
	        }
	        return "redirect:/login?logout";
	    }

}