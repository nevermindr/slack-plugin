package jenkins.plugins.slack.config;

import hudson.model.AbstractProject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import jenkins.plugins.slack.CommitInfoChoice;
import jenkins.plugins.slack.SlackNotifier;
import jenkins.plugins.slack.SlackNotifier.SlackJobProperty;

import jenkins.plugins.slack.SlackNotifierConfigJob;
import org.apache.commons.lang.StringUtils;

/**
 * Configuration migrator for migrating the Slack plugin configuration for a
 * {@link AbstractProject} from the 1.8 format to the 2.3 format.
 *
 * For 1.8: removes the SlackJobProperty from the job properties (if there is one) and
 * moves the Slack notification settings to a {@link SlackNotifier} in the list
 * of publishers (if there is one).
 *
 * For 2.2: renames `authToken` and `authTokenCredentialId` fields to
 * `token` and `tokenCredentialId` respectively.
 */
@SuppressWarnings("deprecation")
public class AbstractProjectConfigMigrator {

    private static final Logger logger = Logger.getLogger(AbstractProjectConfigMigrator.class
            .getName());

    public void migrate(final AbstractProject<?, ?> project) {

        logger.info(String.format("Migrating project \"%s\" with type %s", project.getName(),
                project.getClass().getName()));

        final SlackJobProperty slackJobProperty = project.getProperty(SlackJobProperty.class);
        SlackNotifier slackNotifier = project.getPublishersList().get(SlackNotifier.class);



        try {
            migrate1_8to2_0(project, slackJobProperty, slackNotifier);
            migrate2_2to2_3(project, slackNotifier);


            // property section is not used anymore - remove
            project.save();
            logger.info("Configuration saved successfully");
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void migrate2_2to2_3(AbstractProject<?, ?> project, SlackNotifier slackNotifier) {
        if (slackNotifier == null) {
            logger.info(String.format(
                    "Configuration does not have a notifier for \"%s\", not migrating settings",
                    project.getName()));
        } else {
            if (StringUtils.isBlank(slackNotifier.getSlackNotifierConfigJob().getToken())) {
                slackNotifier.getSlackNotifierConfigJob().setToken(slackNotifier.getSlackNotifierConfigJob().getAuthToken());
                slackNotifier.getSlackNotifierConfigJob().setAuthToken(null);
            }

            if (StringUtils.isBlank(slackNotifier.getSlackNotifierConfigJob().getTokenCredentialId())) {
                slackNotifier.getSlackNotifierConfigJob().setTokenCredentialId(slackNotifier.getSlackNotifierConfigJob().getAuthTokenCredentialId());
                slackNotifier.getSlackNotifierConfigJob().setAuthTokenCredentialId(null);
            }
            logger.info("Configuration updated successfully");
        }
    }

    private void migrate1_8to2_0(AbstractProject<?, ?> project, SlackJobProperty slackJobProperty, SlackNotifier slackNotifier) throws IOException {
        if (slackJobProperty == null) {
            logger.finest(String.format(
                    "Configuration is already up to date for \"%s\", skipping 1.8 migration",
                    project.getName()));
            return;
        }

        if (slackNotifier == null) {
            logger.info(String.format(
                    "Configuration does not have a notifier for \"%s\", not migrating settings",
                    project.getName()));
        } else {
            updateSlackNotifierfromSlackJobProperty(slackNotifier, slackJobProperty);
            logger.info("Configuration updated successfully");

        }

        project.removeProperty(SlackJobProperty.class);
        logger.info("Removed SlackJobProperty section");
    }

    private void updateSlackNotifierfromSlackJobProperty(final SlackNotifier slackNotifier,
                                                         final SlackJobProperty slackJobProperty) {

        SlackNotifierConfigJob slackNotifierConfigJob = slackNotifier.getSlackNotifierConfigJob();
        
        if (StringUtils.isBlank(slackNotifierConfigJob.getTeamDomain())) {
            slackNotifierConfigJob.setTeamDomain(slackJobProperty.getTeamDomain());
        }
        if (StringUtils.isBlank(slackNotifierConfigJob.getToken())) {
            slackNotifierConfigJob.setToken(slackJobProperty.getToken());
        }
        if (StringUtils.isBlank(slackNotifierConfigJob.getRoom())) {
            slackNotifierConfigJob.setRoom(slackJobProperty.getRoom());
        }

        slackNotifierConfigJob.setStartNotification(slackJobProperty.getStartNotification());

        slackNotifierConfigJob.setNotifyAborted(slackJobProperty.getNotifyAborted());
        slackNotifierConfigJob.setNotifyFailure(slackJobProperty.getNotifyFailure());
        slackNotifierConfigJob.setNotifyNotBuilt(slackJobProperty.getNotifyNotBuilt());
        slackNotifierConfigJob.setNotifySuccess(slackJobProperty.getNotifySuccess());
        slackNotifierConfigJob.setNotifyUnstable(slackJobProperty.getNotifyUnstable());
        slackNotifierConfigJob.setNotifyRegression(slackJobProperty.getNotifyRegression());
        slackNotifierConfigJob.setNotifyBackToNormal(slackJobProperty.getNotifyBackToNormal());
        slackNotifierConfigJob.setNotifyRepeatedFailure(slackJobProperty.getNotifyRepeatedFailure());

        slackNotifierConfigJob.setIncludeTestSummary(slackJobProperty.includeTestSummary());
        slackNotifierConfigJob.setCommitInfoChoice(getCommitInfoChoice(slackJobProperty));
        slackNotifierConfigJob.setIncludeCustomMessage(slackJobProperty.includeCustomMessage());
        slackNotifierConfigJob.setCustomMessage(slackJobProperty.getCustomMessage());
    }

    private CommitInfoChoice getCommitInfoChoice(final SlackJobProperty slackJobProperty) {
        if (slackJobProperty.getShowCommitList()) {
            return CommitInfoChoice.AUTHORS_AND_TITLES;
        } else {
            return CommitInfoChoice.NONE;
        }
    }
}
