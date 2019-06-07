package com.uberaemos.ekmeksizdiettracker.model.message;

/**
 * Authentication response sent by the AuthenticationController
 * to frontend
 */
public class JwtResponse {
	// JWT token
	private String token;
	
	// Token type for access token
    private String type = "Bearer";
    
    // Informs the frontend if the authenticated user is admin or not
    private Boolean isAdmin;
 
    public JwtResponse(String accessToken, Boolean isAdmin) {
        this.token = accessToken;
        this.isAdmin = isAdmin;
    }
 
    public String getAccessToken() {
        return token;
    }
 
    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }
 
    public String getTokenType() {
        return type;
    }
 
    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setRole(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}