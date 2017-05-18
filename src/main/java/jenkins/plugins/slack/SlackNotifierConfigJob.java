package jenkins.plugins.slack;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

import java.util.logging.Logger;

public class SlackNotifierConfigJob extends SlackNotifierConfigGlobal {
    private boolean startNotification;
    private boolean notifyAborted;
    private boolean notifyFailure;
    private boolean notifyNotBuilt;
    private boolean notifySuccess;
    private boolean notifyUnstable;
    private boolean notifyRegression;
    private boolean notifyBackToNormal;
    private boolean notifyRepeatedFailure;
    private boolean includeTestSummary;
    private boolean includeFailedTests;
    private CommitInfoChoice commitInfoChoice;
    private boolean includeCustomMessage;
    private String customMessage;

    protected String token;
    protected String tokenCredentialId;

    //this fields are here to support 1.8-2.x migration
    @Deprecated
    private String authToken;
    @Deprecated
    private String authTokenCredentialId;

    public SlackNotifierConfigJob() {

    }

    public SlackNotifierConfigJob(String baseUrl, String teamDomain, String authToken, String authTokenCredentialId, boolean botUser, String room, String sendAs, boolean startNotification, boolean notifyAborted, boolean notifyFailure, boolean notifyNotBuilt, boolean notifySuccess, boolean notifyUnstable, boolean notifyRegression, boolean notifyBackToNormal, boolean notifyRepeatedFailure, boolean includeTestSummary, boolean includeFailedTests, CommitInfoChoice commitInfoChoice, boolean includeCustomMessage, String customMessage) {
        super(baseUrl, teamDomain, authToken, authTokenCredentialId, botUser, room, sendAs);

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

    @Deprecated
    public String getAuthToken() {
        return authToken;
    }
    @Deprecated
    public String getAuthTokenCredentialId() {
        return authTokenCredentialId;
    }
    @Deprecated
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
    @Deprecated
    public void setAuthTokenCredentialId(String authTokenCredentialId) {
        this.authTokenCredentialId = authTokenCredentialId;
    }


    public String getToken() {
        return this.token;
    }

    public String getTokenCredentialId() {
        return this.tokenCredentialId;
    }

    public void setToken(String authToken) {
        this.token = authToken;
    }

    public void setTokenCredentialId(String authTokenCredentialId) {
        this.tokenCredentialId = authTokenCredentialId;
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

    public void setStartNotification(boolean startNotification) {
        this.startNotification = startNotification;
    }

    public void setNotifyAborted(boolean notifyAborted) {
        this.notifyAborted = notifyAborted;
    }

    public void setNotifyFailure(boolean notifyFailure) {
        this.notifyFailure = notifyFailure;
    }

    public void setNotifyNotBuilt(boolean notifyNotBuilt) {
        this.notifyNotBuilt = notifyNotBuilt;
    }

    public void setNotifySuccess(boolean notifySuccess) {
        this.notifySuccess = notifySuccess;
    }

    public void setNotifyUnstable(boolean notifyUnstable) {
        this.notifyUnstable = notifyUnstable;
    }

    public void setNotifyRegression(boolean notifyRegression) {
        this.notifyRegression = notifyRegression;
    }

    public void setNotifyBackToNormal(boolean notifyBackToNormal) {
        this.notifyBackToNormal = notifyBackToNormal;
    }

    public void setNotifyRepeatedFailure(boolean notifyRepeatedFailure) {
        this.notifyRepeatedFailure = notifyRepeatedFailure;
    }

    public void setIncludeTestSummary(boolean includeTestSummary) {
        this.includeTestSummary = includeTestSummary;
    }

    public void setIncludeFailedTests(boolean includeFailedTests) {
        this.includeFailedTests = includeFailedTests;
    }

    public void setCommitInfoChoice(CommitInfoChoice commitInfoChoice) {
        this.commitInfoChoice = commitInfoChoice;
    }

    public void setIncludeCustomMessage(boolean includeCustomMessage) {
        this.includeCustomMessage = includeCustomMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    public static class SlackNotifierConfigJobConverter extends ReflectionConverter {
        private static final Logger logger = Logger.getLogger(SlackNotifierConfigJobConverter.class.getName());

        public SlackNotifierConfigJobConverter(Mapper mapper, ReflectionProvider reflectionProvider) {
            super(mapper, reflectionProvider);
        }

        protected Object instantiateNewInstance(HierarchicalStreamReader reader, UnmarshallingContext context) {
            return this.reflectionProvider.newInstance(SlackNotifierConfigJob.class);
        }

        public void marshal(Object source, HierarchicalStreamWriter writer,
                            MarshallingContext context) {

            SlackNotifierConfigJob slackNotifierConfigJob = ((SlackNotifier)source).getSlackNotifierConfigJob();

            logger.fine(String.format("token: %s", slackNotifierConfigJob.getToken()));
            logger.fine(String.format("SlackNotifierConfigGlobal.token: %s", ((SlackNotifierConfigGlobal)slackNotifierConfigJob).getToken()));

            logger.fine(String.format("baseURL: %s", slackNotifierConfigJob.getBaseUrl()));
            logger.fine(String.format("room: %s", slackNotifierConfigJob.getRoom()));

            super.marshal(slackNotifierConfigJob, writer, context);
        }

        public Object unmarshal(HierarchicalStreamReader reader,
                                UnmarshallingContext context) {

            SlackNotifierConfigJob slackNotifierConfigJob = (SlackNotifierConfigJob) super.unmarshal(reader, context);

            logger.fine(String.format("token: %s", slackNotifierConfigJob.getToken()));
            logger.fine(String.format("baseURL: %s", slackNotifierConfigJob.getBaseUrl()));
            logger.fine(String.format("room: %s", slackNotifierConfigJob.getRoom()));


            SlackNotifier notifier = new SlackNotifier();
            notifier.setSlackNotifierConfigJob(slackNotifierConfigJob);
            return notifier;
        }

        public boolean canConvert(Class type) {
            return type.equals(SlackNotifier.class);
        }
    }
}
