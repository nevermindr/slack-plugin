package jenkins.plugins.slack;

public class SlackNotifierConfigGlobal {
    private final String baseUrl;
    private final String teamDomain;
    private final String token;
    private final String authTokenCredentialId;
    private final boolean botUser;
    private final String roomId;

    public SlackNotifierConfigGlobal(String baseUrl, String teamDomain, String token, String authTokenCredentialId, boolean botUser, String roomId) {
        this.baseUrl = baseUrl;
        this.teamDomain = teamDomain;
        this.token = token;
        this.authTokenCredentialId = authTokenCredentialId;
        this.botUser = botUser;
        this.roomId = roomId;
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
}
