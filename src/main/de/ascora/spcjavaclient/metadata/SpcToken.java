package de.ascora.spcjavaclient.metadata;

/**
 * Created by tobi on 24.11.2015.
 */
public class SpcToken {

    private AccessToken accessToken;
    private RefreshToken refreshToken;

    public SpcToken(AccessToken accessToken, RefreshToken refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessTokenString() {
        return this.accessToken.getValue();
    }

    public String getRefreshTokenString() {
        return this.refreshToken.getValue();
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public RefreshToken getRefreshToken() {
        return refreshToken;
    }
}
