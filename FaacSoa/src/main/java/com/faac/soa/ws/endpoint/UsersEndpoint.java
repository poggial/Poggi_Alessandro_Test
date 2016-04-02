package com.faac.soa.ws.endpoint;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.faac.soa.wrapper.SoaWrapper;
import com.faac.soa.ws.users.GetUsersRequest;
import com.faac.soa.ws.users.GetUsersResponse;

/**
 * USersEndPoint è la classe che identifica l'endpoint del servizio
 * per una chiamata di tipo SOAP.
 * L'uso specifico di questo endpoint è riferito alla lista degli utenti
 * @author Alessandro Poggi
 * 
 */

@Endpoint
public class UsersEndpoint {
	private static final String NAMESPACE_URI = "http://faac.com/soa/ws/users";
	@Autowired
    private SessionRegistry sessionRegistry; 
	
	/**
	 * Ritorna la lista degli utenti connessi
	 *  
	 * @param request è il messaggio di input per invocare la chiamata soap 
	 * @return il messaggio di output contenente la lista degli utenti
	 * @see GetUsersResponse
	 * @see getUsersRequest
	 * 	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsersRequest")
	@ResponsePayload
	public GetUsersResponse getUsers(@RequestPayload GetUsersRequest request) {
		GetUsersResponse response = new GetUsersResponse();
		for(Object o:sessionRegistry.getAllPrincipals()){
		Principal p = (Principal) o;
		response.getUser().add(SoaWrapper.parse(p));
		}
		return response;
	}
	
	
}
