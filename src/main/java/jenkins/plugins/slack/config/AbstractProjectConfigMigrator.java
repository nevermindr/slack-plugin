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
 * {@link AbstractProject} from the 1.8 format to the 2.0 format. It does so by
 * removing the SlackJobProperty from the job properties (if there is one) and
 * moving the Slack notification settings to a {@link SlackNotifier} in the list
 * of publishers (if there is one).
 */
@SuppressWarnings("deprecation")
public class AbstractProjectConfigMigrator {

    private static final Logger logger = Logger.getLogger(AbstractProjectConfigMigrator.class
            .getName());

    public void migrate(final AbstractProject<?, ?> project) {

        logger.info(String.format("Migrating project \"%s\" with type %s", project.getName(),
                project.getClass().getName()));

        final SlackJobProperty slackJobProperty = project.getProperty(SlackJobProperty.class);

        if (slackJobProperty == null) {
            logger.finest(String.format(
                    "Configuration is already up to date for \"%s\", skipping migration",
                    project.getName()));
            return;
        }

        SlackNotifier slackNotifier = project.getPublishersList().get(SlackNotifier.class);

        if (slackNotifier == null) {
            logger.info(String.format(
                    "Configuration does not have a notifier for \"%s\", not migrating settings",
                    project.getName()));
        } else {
            updateSlackNotifier(slackNotifier, slackJobProperty);
        }

        try {
            // property section is not used anymore - remove
            project.removeProperty(SlackJobProperty.class);
            project.save();
            logger.info("Configuration updated successfully");
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updateSlackNotifier(final SlackNotifier slackNotifier,
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
