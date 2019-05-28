package com.uberaemos.ekmeksizdiettracker.model.message;

public class JwtResponse {
	private String token;
    private String type = "Bearer";
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
