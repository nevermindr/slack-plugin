package jenkins.plugins.slack;

public class SlackNotifierConfigJob {
    private final String baseUrl;
    private final String teamDomain;
    private final String authToken;
    private final boolean botUser;
    private final String room;
    private final String authTokenCredentialId;
    private final String sendAs;
    private final boolean startNotification;
    private final boolean notifyAborted;
    private final boolean notifyFailure;
    private final boolean notifyNotBuilt;
    private final boolean notifySuccess;
    private final boolean notifyUnstable;
    private final boolean notifyRegression;
    private final boolean notifyBackToNormal;
    private final boolean notifyRepeatedFailure;
    private final boolean includeTestSummary;
    private final boolean includeFailedTests;
    private final CommitInfoChoice commitInfoChoice;
    private final boolean includeCustomMessage;
    private final String customMessage;

    public SlackNotifierConfigJob(String baseUrl, String teamDomain, String authToken, boolean botUser, String room, String authTokenCredentialId, String sendAs, boolean startNotification, boolean notifyAborted, boolean notifyFailure, boolean notifyNotBuilt, boolean notifySuccess, boolean notifyUnstable, boolean notifyRegression, boolean notifyBackToNormal, boolean notifyRepeatedFailure, boolean includeTestSummary, boolean includeFailedTests, CommitInfoChoice commitInfoChoice, boolean includeCustomMessage, String customMessage) {
        this.baseUrl = baseUrl;
        this.teamDomain = teamDomain;
        this.authToken = authToken;
        this.botUser = botUser;
        this.room = room;
        this.authTokenCredentialId = authTokenCredentialId;
        this.sendAs = sendAs;
        this.startNotification = startNotification;
        this.notifyAborted = notifyAborted;
        this.notifyFailure = notifyFailure;
        this.notifyNotBuilt = notifyNotBuilt;
        this.notifySuccess = notifySuccess;
        this.notifyUnstable = notifyUnstable;
        this.notifyRegression = notifyRegression;
        this.notifyBackToNormal = notifyBackToNormal;
        this.notifyRepeatedFailure = notifyRepeatedFailure;
        this.includeTestSummary = includeTestSummary;
        this.includeFailedTests = includeFailedTests;
        this.commitInfoChoice = commitInfoChoice;
        this.includeCustomMessage = includeCustomMessage;
        this.customMessage = customMessage;
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

    public boolean isBotUser() {
        return botUser;
    }

    public String getRoom() {
        return room;
    }

    public String getAuthTokenCredentialId() {
        return authTokenCredentialId;
    }

    public String getSendAs() {
        return sendAs;
    }

    public boolean isStartNotification() {
        return startNotification;
    }

    public boolean isNotifyAborted() {
        return notifyAborted;
    }

    public boolean isNotifyFailure() {
        return notifyFailure;
    }

    public boolean isNotifyNotBuilt() {
        return notifyNotBuilt;
    }

    public boolean isNotifySuccess() {
        return notifySuccess;
    }

    public boolean isNotifyUnstable() {
        return notifyUnstable;
    }

    public boolean isNotifyRegression() {
        return notifyRegression;
    }

    public boolean isNotifyBackToNormal() {
        return notifyBackToNormal;
    }

    public boolean isNotifyRepeatedFailure() {
        return notifyRepeatedFailure;
    }

    public boolean isIncludeTestSummary() {
        return includeTestSummary;
    }

    public boolean isIncludeFailedTests() {
        return includeFailedTests;
    }

    public CommitInfoChoice getCommitInfoChoice() {
        return commitInfoChoice;
    }

    public boolean isIncludeCustomMessage() {
        return includeCustomMessage;
    }

    public String getCustomMessage() {
        return customMessage;
    }
}
