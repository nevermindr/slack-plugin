package jenkins.plugins.slack;

public class SlackNotifierStub extends SlackNotifier {

    public SlackNotifierStub(String baseUrl, String teamDomain, String authToken, boolean botUser, String room, String authTokenCredentialId,
                             String sendAs, boolean startNotification, boolean notifyAborted, boolean notifyFailure,
                             boolean notifyNotBuilt, boolean notifySuccess, boolean notifyUnstable, boolean notifyRegression, boolean notifyBackToNormal,
                             boolean notifyRepeatedFailure, boolean includeTestSummary, boolean includeFailedTests, 
                             CommitInfoChoice commitInfoChoice, boolean includeCustomMessage, String customMessage) {
        super(
                new SlackNotifierConfigJob(baseUrl, teamDomain, authToken, authTokenCredentialId, botUser, room, sendAs, startNotification, notifyAborted, notifyFailure, notifyNotBuilt, notifySuccess, notifyUnstable, notifyRegression, notifyBackToNormal, notifyRepeatedFailure, includeTestSummary, includeFailedTests, commitInfoChoice, includeCustomMessage, customMessage));
    }

    public static class DescriptorImplStub extends SlackNotifier.DescriptorImpl {

        private SlackService slackService;

        @Override
        public synchronized void load() {
        }

        @Override
        SlackService getSlackService() {
            return slackService;
        }

        @Override
        SlackService getSlackService(SlackNotifierConfigGlobal slackNotifierConfigGlobal) {
            return slackService;
        }

        public void setSlackService(SlackService slackService) {
            this.slackService = slackService;
        }
    }
}
