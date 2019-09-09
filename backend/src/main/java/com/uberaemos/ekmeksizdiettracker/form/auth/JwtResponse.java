package com.uberaemos.ekmeksizdiettracker.form.auth;

/**
 * Authentication response sent by the AuthenticationController
 * to frontend
 */
public final class JwtResponse {
	// JWT token
	private String token;
	
	// Token type for access token
    private String type = "Bearer";
    
    public JwtResponse(String accessToken) {
        this.token = accessToken;
    }
 
    public String getAccessToken() {return token;}
    public String getTokenType() {return type;}
}