package com.upgrad.patterns.Authentication;


import com.upgrad.patterns.Middleware.AuthenticationProcessor;
import com.upgrad.patterns.Middleware.BasicAuthProcessor;
import com.upgrad.patterns.Middleware.JwtAuthProcessor;

import javax.servlet.http.HttpServletRequest;

public class Authenticator extends AuthenticationProcessor {
	
//create a public static method GetAuthProcessor of the return type AuthenticationProcessor
	// create an object of type JwtAuthProcessor
    public static JwtAuthProcessor nextProcessor;

    public Authenticator(AuthenticationProcessor nextProcessor) {
        super(nextProcessor);

    }
    // Chain Authentication processors, first JWT processor is to be used first and then basic auth processor
	// return the object

    public static AuthenticationProcessor GetAuthProcessor() {

        AuthenticationProcessor JwtProcessor = new JwtAuthProcessor(null);
        AuthenticationProcessor basicAuthProcessor = new BasicAuthProcessor(JwtProcessor);
        return basicAuthProcessor;
    }
    public static AuthenticationProvider GetAuthProvider(HttpServletRequest request)
    {
        if(request.getHeader("Authorization") != null)
            return new JwtAuthProvider(request.getHeader("Authorization"));
        return new BasicAuthProvider(request.getHeader("Username"), request.getHeader("Password"));
    }




    @Override
    public boolean isAuthorized(AuthenticationProvider provider) {
        if (provider instanceof JwtAuthProvider) {
            return Boolean.TRUE;
        } else if (nextProcessor != null) {
            return nextProcessor.isAuthorized(provider);
        } else {
            return Boolean.FALSE;
        }
    }
}
