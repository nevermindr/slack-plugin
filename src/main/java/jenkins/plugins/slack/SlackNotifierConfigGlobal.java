package jenkins.plugins.slack;

import org.apache.commons.lang.StringUtils;

public class SlackNotifierConfigGlobal {
    protected String baseUrl;
    protected String teamDomain;
    protected String token;
    protected String authTokenCredentialId;
    protected boolean botUser;
    protected String roomId;
    protected String[] roomIds;

    public SlackNotifierConfigGlobal(String baseUrl, String teamDomain, String token, String authTokenCredentialId, boolean botUser, String roomId) {

        if(baseUrl != null && !baseUrl.isEmpty() && !baseUrl.endsWith("/")) {
            baseUrl += "/";
        }

        this.baseUrl = baseUrl;
        this.teamDomain = teamDomain;
        this.token = token;
        this.authTokenCredentialId = StringUtils.trim(authTokenCredentialId);
        this.botUser = botUser;
        this.roomId = roomId;
        this.roomIds = roomId.split("[,; ]+");
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getTeamDomain() {
        return teamDomain;
    }

    public String getToken() {
        return token;
    }

    public String getAuthTokenCredentialId() {
        return authTokenCredentialId;
    }

    public boolean isBotUser() {
        return botUser;
    }

    public String getRoomId() {
        return roomId;
    }

    public String[] getRoomIds() {
        return roomIds;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setTeamDomain(String teamDomain) {
        this.teamDomain = teamDomain;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setAuthTokenCredentialId(String authTokenCredentialId) {
        this.authTokenCredentialId = authTokenCredentialId;
    }

    public void setBotUser(boolean botUser) {
        this.botUser = botUser;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setRoomIds(String[] roomIds) {
        this.roomIds = roomIds;
    }
}
