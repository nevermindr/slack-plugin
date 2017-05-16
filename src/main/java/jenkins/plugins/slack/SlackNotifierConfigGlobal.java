package jenkins.plugins.slack;

import org.apache.commons.lang.StringUtils;

public class SlackNotifierConfigGlobal {
    protected final String baseUrl;
    protected final String teamDomain;
    protected final String token;
    protected final String authTokenCredentialId;
    protected final boolean botUser;
    protected final String roomId;
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
}
