package jenkins.plugins.slack;

import org.apache.commons.lang.StringUtils;

public class SlackNotifierConfigGlobal {
    protected String baseUrl;
    protected String teamDomain;
    protected String authToken;
    protected String authTokenCredentialId;
    protected boolean botUser;
    protected String room;
    protected String buildServerUrl;
    protected String[] roomIds;

    public SlackNotifierConfigGlobal() {

    }

    public SlackNotifierConfigGlobal(String baseUrl, String teamDomain, String authToken, String authTokenCredentialId, boolean botUser, String room) {

        if(baseUrl != null && !baseUrl.isEmpty() && !baseUrl.endsWith("/")) {
            baseUrl += "/";
        }

        this.baseUrl = baseUrl;
        this.teamDomain = teamDomain;
        this.authToken = authToken;
        this.authTokenCredentialId = StringUtils.trim(authTokenCredentialId);
        this.botUser = botUser;
        this.room = room;
        this.roomIds = room.split("[,; ]+");
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getTeamDomain() {
        return teamDomain;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getAuthTokenCredentialId() {
        return authTokenCredentialId;
    }

    public boolean isBotUser() {
        return botUser;
    }

    public String getRoom() {
        return room;
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

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setAuthTokenCredentialId(String authTokenCredentialId) {
        this.authTokenCredentialId = authTokenCredentialId;
    }

    public void setBotUser(boolean botUser) {
        this.botUser = botUser;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setRoomIds(String[] roomIds) {
        this.roomIds = roomIds;
    }

    public String getBuildServerUrl() {
        return buildServerUrl;
    }

    public void setBuildServerUrl(String buildServerUrl) {
        this.buildServerUrl = buildServerUrl;
    }

}
