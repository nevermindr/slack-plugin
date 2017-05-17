package jenkins.plugins.slack;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

public class SlackNotifierConfigJob extends SlackNotifierConfigGlobal {
    private String sendAs;
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

    public SlackNotifierConfigJob() {

    }

    public SlackNotifierConfigJob(String baseUrl, String teamDomain, String authToken, String authTokenCredentialId, boolean botUser, String room, String sendAs, boolean startNotification, boolean notifyAborted, boolean notifyFailure, boolean notifyNotBuilt, boolean notifySuccess, boolean notifyUnstable, boolean notifyRegression, boolean notifyBackToNormal, boolean notifyRepeatedFailure, boolean includeTestSummary, boolean includeFailedTests, CommitInfoChoice commitInfoChoice, boolean includeCustomMessage, String customMessage) {
        super(baseUrl, teamDomain, authToken, authTokenCredentialId, botUser, room);


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

    public void setSendAs(String sendAs) {
        this.sendAs = sendAs;
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
        public SlackNotifierConfigJobConverter(Mapper mapper, ReflectionProvider reflectionProvider) {
            super(mapper, reflectionProvider);
        }

        protected Object instantiateNewInstance(HierarchicalStreamReader reader, UnmarshallingContext context) {
            return this.reflectionProvider.newInstance(SlackNotifierConfigJob.class);
        }

        public void marshal(Object source, HierarchicalStreamWriter writer,
                            MarshallingContext context) {

            super.marshal(((SlackNotifier)source).getSlackNotifierConfigJob(), writer, context);
        }

        public Object unmarshal(HierarchicalStreamReader reader,
                                UnmarshallingContext context) {

            SlackNotifierConfigJob slackNotifierConfigJob = (SlackNotifierConfigJob) super.unmarshal(reader, context);

            SlackNotifier notifier = new SlackNotifier();
            notifier.setSlackNotifierConfigJob(slackNotifierConfigJob);
            return notifier;
        }

        public boolean canConvert(Class type) {
            return type.equals(SlackNotifier.class);
        }
    }
}
