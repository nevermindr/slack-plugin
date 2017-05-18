package jenkins.plugins.slack;

import com.cloudbees.plugins.credentials.common.StandardListBoxModel;
import com.cloudbees.plugins.credentials.domains.HostnameRequirement;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import hudson.EnvVars;
import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.Item;
import hudson.model.Descriptor;
import hudson.model.listeners.ItemListener;
import hudson.security.ACL;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Notifier;
import hudson.tasks.Publisher;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;
import jenkins.model.Jenkins;
import jenkins.plugins.slack.config.ItemConfigMigrator;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.jenkinsci.plugins.displayurlapi.DisplayURLProvider;
import org.jenkinsci.plugins.plaincredentials.StringCredentials;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.export.Exported;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import static com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials;

@XStreamConverter(SlackNotifierConfigJob.SlackNotifierConfigJobConverter.class)
public class SlackNotifier extends Notifier {

    private static final Logger logger = Logger.getLogger(SlackNotifier.class.getName());


    private SlackNotifierConfigJob slackNotifierConfigJob;


    public SlackNotifierConfigJob getSlackNotifierConfigJob() {
        return slackNotifierConfigJob;
    }

    public void setSlackNotifierConfigJob(SlackNotifierConfigJob slackNotifierConfigJob) {
        this.slackNotifierConfigJob = slackNotifierConfigJob;
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl)super.getDescriptor();
    }

    public String getBaseUrl() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.getBaseUrl():null;
    }

    public String getTeamDomain() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.getTeamDomain():null;
    }

    @DataBoundSetter
    public void setTeamDomain(final String teamDomain) {
        this.slackNotifierConfigJob.setTeamDomain(teamDomain);
    }

    public String getRoom() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.getRoom():null;
    }

    @DataBoundSetter
    public void setRoom(String room) {
        this.slackNotifierConfigJob.setRoom(room);
    }

    public String getToken() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.getToken():null;
    }

    @DataBoundSetter
    public void setToken(String authToken) {
        this.slackNotifierConfigJob.setToken(authToken);
    }

    public String getTokenCredentialId() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.getTokenCredentialId():null;
    }

    @DataBoundSetter
    public void setTokenCredentialId(String authTokenCredentialId) {
        this.slackNotifierConfigJob.setTokenCredentialId(authTokenCredentialId);
    }

    public boolean getBotUser() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.isBotUser():false;
    }

    @DataBoundSetter
    public void setBotUser(boolean botUser) {
        this.slackNotifierConfigJob.setBotUser(botUser);
    }

    public String getSendAs() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.getSendAs():null;
    }

    @DataBoundSetter
    public void setSendAs(String sendAs) {
        this.slackNotifierConfigJob.setSendAs(sendAs);
    }

    public boolean getStartNotification() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.isStartNotification():false;
    }

    public boolean getNotifySuccess() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.isNotifySuccess():false;
    }

    public CommitInfoChoice getCommitInfoChoice() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.getCommitInfoChoice():null;
    }

    public boolean getNotifyAborted() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.isNotifyAborted():false;
    }

    public boolean getNotifyFailure() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.isNotifyFailure():false;
    }

    public boolean getNotifyNotBuilt() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.isNotifyNotBuilt():false;
    }

    public boolean getNotifyUnstable() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.isNotifyUnstable():false;
    }

    public boolean getNotifyRegression() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.isNotifyRegression():false;
    }

    public boolean getNotifyBackToNormal() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.isNotifyBackToNormal():false;
    }

    public boolean getIncludeTestSummary() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.isIncludeTestSummary():false;
    }

    public boolean isIncludeFailedTests() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.isIncludeFailedTests():false;
    }

    public boolean getNotifyRepeatedFailure() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.isNotifyRepeatedFailure():false;
    }

    public boolean getIncludeCustomMessage() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.isIncludeCustomMessage():false;
    }

    public String getCustomMessage() {
        return slackNotifierConfigJob!=null?slackNotifierConfigJob.getCustomMessage():null;
    }

    @DataBoundSetter
    public void setStartNotification(boolean startNotification) {
        this.slackNotifierConfigJob.setStartNotification(startNotification);
    }

    @DataBoundSetter
    public void setNotifySuccess(boolean notifySuccess) {
        this.slackNotifierConfigJob.setNotifySuccess(notifySuccess);
    }

    @DataBoundSetter
    public void setCommitInfoChoice(CommitInfoChoice commitInfoChoice) {
        this.slackNotifierConfigJob.setCommitInfoChoice(commitInfoChoice);
    }

    @DataBoundSetter
    public void setNotifyAborted(boolean notifyAborted) {
        this.slackNotifierConfigJob.setNotifyAborted(notifyAborted);
    }

    @DataBoundSetter
    public void setNotifyFailure(boolean notifyFailure) {
        this.slackNotifierConfigJob.setNotifyFailure(notifyFailure);
    }

    @DataBoundSetter
    public void setNotifyNotBuilt(boolean notifyNotBuilt) {
        this.slackNotifierConfigJob.setNotifyNotBuilt(notifyNotBuilt);
    }

    @DataBoundSetter
    public void setNotifyUnstable(boolean notifyUnstable) {
        this.slackNotifierConfigJob.setNotifyUnstable(notifyUnstable);
    }

    @DataBoundSetter
    public void setNotifyRegression(boolean notifyRegression) {
        this.slackNotifierConfigJob.setNotifyRegression(notifyRegression);
    }

    @DataBoundSetter
    public void setNotifyBackToNormal(boolean notifyBackToNormal) {
        this.slackNotifierConfigJob.setNotifyBackToNormal(notifyBackToNormal);
    }

    @DataBoundSetter
    public void setIncludeTestSummary(boolean includeTestSummary) {
        this.slackNotifierConfigJob.setIncludeTestSummary(includeTestSummary);
    }

    @DataBoundSetter
    public void setNotifyRepeatedFailure(boolean notifyRepeatedFailure) {
        this.slackNotifierConfigJob.setNotifyRepeatedFailure(notifyRepeatedFailure);
    }

    @DataBoundSetter
    public void setIncludeCustomMessage(boolean includeCustomMessage) {
        this.slackNotifierConfigJob.setIncludeCustomMessage(includeCustomMessage);
    }

    @DataBoundSetter
    public void setCustomMessage(String customMessage) {
        this.slackNotifierConfigJob.setCustomMessage(customMessage);
    }

    @DataBoundConstructor
    public SlackNotifier() {
        super();
    }

    public SlackNotifier(SlackNotifierConfigJob _slackNotifierConfigJob) {
        super();
        this.slackNotifierConfigJob = _slackNotifierConfigJob;

    }

    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.NONE;
    }

    public SlackService newSlackService(AbstractBuild r, BuildListener listener) {
        String baseUrl = slackNotifierConfigJob.getBaseUrl();
        String teamDomain = slackNotifierConfigJob.getTeamDomain();
        String authToken = slackNotifierConfigJob.getToken();
        String  authTokenCredentialId = slackNotifierConfigJob.getTokenCredentialId();
        boolean botUser = slackNotifierConfigJob.isBotUser();
        String room = slackNotifierConfigJob.getRoom();

        if (StringUtils.isEmpty(teamDomain)) {
            teamDomain = getDescriptor().getTeamDomain();
        }

        if (StringUtils.isEmpty(baseUrl)) {
            baseUrl = getDescriptor().getBaseUrl();
        }

        if (StringUtils.isEmpty(authToken)) {
            authToken = getDescriptor().getToken();
            botUser = getDescriptor().getBotUser();
        }
        if (StringUtils.isEmpty(authTokenCredentialId)) {
            authTokenCredentialId = getDescriptor().getTokenCredentialId();
        }
        if (StringUtils.isEmpty(room)) {
            room = getDescriptor().getRoom();
        }

        EnvVars env = null;
        try {
            env = r.getEnvironment(listener);
        } catch (Exception e) {
            listener.getLogger().println("Error retrieving environment vars: " + e.getMessage());
            env = new EnvVars();
        }
        baseUrl = env.expand(baseUrl);
        teamDomain = env.expand(teamDomain);
        authToken = env.expand(authToken);
        authTokenCredentialId = env.expand(authTokenCredentialId);
        room = env.expand(room);

        return new StandardSlackService(new SlackNotifierConfigGlobal(baseUrl, teamDomain, authToken, authTokenCredentialId, botUser, room, ""));
    }

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {
        return true;
    }

    @Override
    public boolean prebuild(AbstractBuild<?, ?> build, BuildListener listener) {
        if (slackNotifierConfigJob.isStartNotification()) {
            Map<Descriptor<Publisher>, Publisher> map = build.getProject().getPublishersList().toMap();
            for (Publisher publisher : map.values()) {
                if (publisher instanceof SlackNotifier) {
                    logger.info("Invoking Started...");
                    new ActiveNotifier((SlackNotifier) publisher, listener).started(build);
                }
            }
        }
        return super.prebuild(build, listener);
    }


    @Extension
    @XStreamConverter(SlackNotifierConfigGlobal.SlackNotifierConfigGlobalConverter.class)
    public static class DescriptorImpl extends BuildStepDescriptor<Publisher> {
        private static final Logger logger = Logger.getLogger(DescriptorImpl.class.getName());


        private SlackNotifierConfigGlobal slackNotifierConfigGlobal;

        public SlackNotifierConfigGlobal getSlackNotifierConfigGlobal() {
            return slackNotifierConfigGlobal;
        }

        public void setSlackNotifierConfigGlobal(SlackNotifierConfigGlobal slackNotifierConfigGlobal) {
            this.slackNotifierConfigGlobal = slackNotifierConfigGlobal;
        }

        public DescriptorImpl() {
            load();
        }

        public String getBaseUrl() {
            return slackNotifierConfigGlobal!=null?slackNotifierConfigGlobal.getBaseUrl():null;
        }

        public String getTeamDomain() {
            return slackNotifierConfigGlobal!=null?slackNotifierConfigGlobal.getTeamDomain():null;
        }

        public String getToken() {
            return slackNotifierConfigGlobal!=null?slackNotifierConfigGlobal.getToken():null;
        }

        public String getTokenCredentialId() {
            return slackNotifierConfigGlobal!=null?slackNotifierConfigGlobal.getTokenCredentialId():null;
        }

        public boolean getBotUser() {
            return slackNotifierConfigGlobal!=null?slackNotifierConfigGlobal.isBotUser():false;
        }

        public String getRoom() {
            return slackNotifierConfigGlobal!=null?slackNotifierConfigGlobal.getRoom():null;
        }

        public String getSendAs() {
            return slackNotifierConfigGlobal!=null?slackNotifierConfigGlobal.getSendAs():null;
        }

        public ListBoxModel doFillCommitInfoChoiceItems() {
            ListBoxModel listBoxModel =  new StandardListBoxModel();
            for (CommitInfoChoice commitInfoChoice : CommitInfoChoice.values()) {
                listBoxModel.add(commitInfoChoice.getDisplayName(), commitInfoChoice.name());
                logger.info(String.format("%s - %s - %s", commitInfoChoice.getDisplayName(), commitInfoChoice.name(), commitInfoChoice));
            }
            return listBoxModel;
        }

        public ListBoxModel doFillTokenCredentialIdItems() {
            if (!Jenkins.getInstance().hasPermission(Jenkins.ADMINISTER)) {
                return new ListBoxModel();
            }
            return new StandardListBoxModel()
                    .withEmptySelection()
                    .withAll(lookupCredentials(
                            StringCredentials.class,
                            Jenkins.getInstance(),
                            ACL.SYSTEM,
                            new HostnameRequirement("*.slack.com"))
                    );
        }

        //WARN users that they should not use the plain/exposed authToken, but rather the authToken credential id
        public FormValidation doCheckToken(@QueryParameter String value) {
            //always show the warning - TODO investigate if there is a better way to handle this
            return FormValidation.warning("Exposing your Integration Token is a security risk. Please use the Integration Token Credential ID");
        }

        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public SlackNotifier newInstance(StaplerRequest sr, JSONObject json) {
            logger.finer("json: " + json.toString());

            SlackNotifierConfigJob _slackNotifierConfigJob = new SlackNotifierConfigJob();
            sr.bindJSON(_slackNotifierConfigJob, json);

            _slackNotifierConfigJob.checkData();

            SlackNotifier sn = new SlackNotifier(_slackNotifierConfigJob);

            return sn;
        }

        @Override
        public boolean configure(StaplerRequest sr, JSONObject formData) throws FormException {
            logger.finer("json: " + formData.toString());

            sr.bindJSON(slackNotifierConfigGlobal, formData);
            logger.finer("token: " + slackNotifierConfigGlobal.getToken());

            slackNotifierConfigGlobal.checkData();

            save();
            return super.configure(sr, formData);
        }

        SlackService getSlackService(final String baseUrl, final String teamDomain, final String authToken, final String authTokenCredentialId, final boolean botUser, final String room) {
            return new StandardSlackService(this.slackNotifierConfigGlobal);
        }

        @Override
        public String getDisplayName() {
            return "Slack Notifications";
        }

        public FormValidation doTestConnection(@QueryParameter("slackBaseUrl") final String baseUrl,
                                               @QueryParameter("slackTeamDomain") final String teamDomain,
                                               @QueryParameter("slackToken") final String authToken,
                                               @QueryParameter("tokenCredentialId") final String authTokenCredentialId,
                                               @QueryParameter("slackBotUser") final boolean botUser,
                                               @QueryParameter("slackRoom") final String room) throws FormException {
            logger.finer(String.format("(%s,%s,%s,%s,%s,%s)", baseUrl, teamDomain, authToken, authTokenCredentialId, botUser, room));
            try {
                String targetUrl = baseUrl;
                if(targetUrl != null && !targetUrl.isEmpty() && !targetUrl.endsWith("/")) {
                    targetUrl += "/";
                }
                if (StringUtils.isEmpty(targetUrl)) {
                    targetUrl = this.getBaseUrl();
                }
                String targetDomain = teamDomain;
                if (StringUtils.isEmpty(targetDomain)) {
                    targetDomain = this.getTeamDomain();
                }
                String targetToken = authToken;
                boolean targetBotUser = botUser;
                if (StringUtils.isEmpty(targetToken)) {
                    targetToken = this.getToken();
                    targetBotUser = this.getBotUser();
                }
                String targetTokenCredentialId = authTokenCredentialId;
                if (StringUtils.isEmpty(targetTokenCredentialId)) {
                    targetTokenCredentialId = this.getTokenCredentialId();
                }
                String targetRoom = room;
                if (StringUtils.isEmpty(targetRoom)) {
                    targetRoom = this.getRoom();
                }
                SlackService testSlackService = getSlackService(targetUrl, targetDomain, targetToken, targetTokenCredentialId, targetBotUser, targetRoom);
                String message = "Slack/Jenkins plugin: you're all set on " + DisplayURLProvider.get().getRoot();
                boolean success = testSlackService.publish(message, "good");
                return success ? FormValidation.ok("Success") : FormValidation.error("Failure");
            } catch (Exception e) {
                return FormValidation.error("Client error : " + e.getMessage());
            }
        }
    }

    @Deprecated
    public static class SlackJobProperty extends hudson.model.JobProperty<AbstractProject<?, ?>> {

        private String teamDomain;
        private String token;
        private boolean botUser;
        private String room;
        private boolean startNotification;
        private boolean notifySuccess;
        private boolean notifyAborted;
        private boolean notifyNotBuilt;
        private boolean notifyUnstable;
        private boolean notifyRegression;
        private boolean notifyFailure;
        private boolean notifyBackToNormal;
        private boolean notifyRepeatedFailure;
        private boolean includeTestSummary;
        private boolean showCommitList;
        private boolean includeCustomMessage;
        private String customMessage;

        @DataBoundConstructor
        public SlackJobProperty(String teamDomain,
                                String token,
                                boolean botUser,
                                String room,
                                boolean startNotification,
                                boolean notifyAborted,
                                boolean notifyFailure,
                                boolean notifyNotBuilt,
                                boolean notifySuccess,
                                boolean notifyUnstable,
                                boolean notifyRegression,
                                boolean notifyBackToNormal,
                                boolean notifyRepeatedFailure,
                                boolean includeTestSummary,
                                boolean showCommitList,
                                boolean includeCustomMessage,
                                String customMessage) {
            this.teamDomain = teamDomain;
            this.token = token;
            this.botUser = botUser;
            this.room = room;
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
            this.showCommitList = showCommitList;
            this.includeCustomMessage = includeCustomMessage;
            this.customMessage = customMessage;
        }

        @Exported
        public String getTeamDomain() {
            return teamDomain;
        }

        @Exported
        public String getToken() {
            return token;
        }

        @Exported
        public boolean getBotUser() {
            return botUser;
        }

        @Exported
        public String getRoom() {
            return room;
        }

        @Exported
        public boolean getStartNotification() {
            return startNotification;
        }

        @Exported
        public boolean getNotifySuccess() {
            return notifySuccess;
        }

        @Exported
        public boolean getShowCommitList() {
            return showCommitList;
        }

        @Override
        public boolean prebuild(AbstractBuild<?, ?> build, BuildListener listener) {
            return super.prebuild(build, listener);
        }

        @Exported
        public boolean getNotifyAborted() {
            return notifyAborted;
        }

        @Exported
        public boolean getNotifyFailure() {
            return notifyFailure;
        }

        @Exported
        public boolean getNotifyNotBuilt() {
            return notifyNotBuilt;
        }

        @Exported
        public boolean getNotifyUnstable() {
            return notifyUnstable;
        }

        @Exported
        public boolean getNotifyRegression() {
            return notifyRegression;
        }

        @Exported
        public boolean getNotifyBackToNormal() {
            return notifyBackToNormal;
        }

        @Exported
        public boolean includeTestSummary() {
            return includeTestSummary;
        }

        @Exported
        public boolean getNotifyRepeatedFailure() {
            return notifyRepeatedFailure;
        }

        @Exported
        public boolean includeCustomMessage() {
            return includeCustomMessage;
        }

        @Exported
        public String getCustomMessage() {
            return customMessage;
        }

    }

    @Extension(ordinal = 100) public static final class Migrator extends ItemListener {
        @Override
        public void onLoaded() {
            logger.info("Starting Settings Migration Process");

            ItemConfigMigrator migrator = new ItemConfigMigrator();

            for (Item item : Jenkins.getInstance().getAllItems()) {
                if (!migrator.migrate(item)) {
                    logger.info(String.format("Skipping job \"%s\" with type %s", item.getName(),
                            item.getClass().getName()));
                    continue;
                }
            }

            logger.info("Completed Settings Migration Process");
        }
    }

}
