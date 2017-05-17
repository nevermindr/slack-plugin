package jenkins.plugins.slack.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import hudson.matrix.MatrixProject;
import hudson.model.FreeStyleProject;
import jenkins.model.Jenkins;
import jenkins.plugins.slack.CommitInfoChoice;
import jenkins.plugins.slack.SlackNotifier;
import jenkins.plugins.slack.SlackNotifier.SlackJobProperty;

import jenkins.plugins.slack.SlackNotifierConfigJob;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.recipes.LocalData;

@SuppressWarnings("deprecation")
public class BackwordsCompatible_1_8_SlackNotifierTest {

    @Rule
    public JenkinsRule j = new JenkinsRule();

    public Jenkins jenkins;

    @Before
    public void setup() {
        jenkins = j.getInstance();
    }

    @Test
    @LocalData
    public void testBasicMigration() {
        FreeStyleProject project = (FreeStyleProject) jenkins.getItem("Test_Slack_Plugin");
        SlackNotifier notifier = project.getPublishersList().get(SlackNotifier.class);
        SlackNotifierConfigJob slackNotifierConfigJob = notifier.getSlackNotifierConfigJob();

        assertEquals("jenkins-slack-plugin", slackNotifierConfigJob.getTeamDomain());
        assertEquals("auth-token-for-test", slackNotifierConfigJob.getAuthToken());
        assertEquals(false, slackNotifierConfigJob.isBotUser());
        assertEquals("#slack-plugin-testing", slackNotifierConfigJob.getRoom());

        assertFalse(slackNotifierConfigJob.isStartNotification());
        assertTrue(slackNotifierConfigJob.isNotifySuccess());
        assertFalse(slackNotifierConfigJob.isNotifyAborted());
        assertFalse(slackNotifierConfigJob.isNotifyNotBuilt());
        assertFalse(slackNotifierConfigJob.isNotifyUnstable());
        assertFalse(slackNotifierConfigJob.isNotifyRegression());
        assertTrue(slackNotifierConfigJob.isNotifyFailure());
        assertFalse(slackNotifierConfigJob.isNotifyBackToNormal());
        assertFalse(slackNotifierConfigJob.isNotifyRepeatedFailure());
        assertFalse(slackNotifierConfigJob.isIncludeTestSummary());
        assertEquals(CommitInfoChoice.NONE, slackNotifierConfigJob.getCommitInfoChoice());
        assertFalse(slackNotifierConfigJob.isIncludeCustomMessage());
        assertEquals("", slackNotifierConfigJob.getCustomMessage());

        assertNull(project.getProperty(SlackJobProperty.class));
    }

    @Test
    @LocalData
    public void testGlobalSettingsOverriden() {
        FreeStyleProject project = (FreeStyleProject) jenkins.getItem("Test_Slack_Plugin");
        SlackNotifier notifier = project.getPublishersList().get(SlackNotifier.class);
        SlackNotifierConfigJob slackNotifierConfigJob = notifier.getSlackNotifierConfigJob();

        assertEquals("jenkins-slack-plugin", slackNotifierConfigJob.getTeamDomain());
        assertEquals("auth-token-for-test", slackNotifierConfigJob.getAuthToken());
        assertEquals(false, slackNotifierConfigJob.isBotUser());
        assertEquals("#slack-plugin-testing", slackNotifierConfigJob.getRoom());

        assertFalse(slackNotifierConfigJob.isStartNotification());
        assertTrue(slackNotifierConfigJob.isNotifySuccess());
        assertFalse(slackNotifierConfigJob.isNotifyAborted());
        assertFalse(slackNotifierConfigJob.isNotifyNotBuilt());
        assertFalse(slackNotifierConfigJob.isNotifyUnstable());
        assertFalse(slackNotifierConfigJob.isNotifyRegression());
        assertTrue(slackNotifierConfigJob.isNotifyFailure());
        assertFalse(slackNotifierConfigJob.isNotifyBackToNormal());
        assertFalse(slackNotifierConfigJob.isNotifyRepeatedFailure());
        assertFalse(slackNotifierConfigJob.isIncludeTestSummary());
        assertEquals(CommitInfoChoice.NONE, slackNotifierConfigJob.getCommitInfoChoice());
        assertFalse(slackNotifierConfigJob.isIncludeCustomMessage());
        assertEquals("", slackNotifierConfigJob.getCustomMessage());

        assertNull(project.getProperty(SlackJobProperty.class));
    }

    @Test
    @LocalData
    public void testGlobalSettingsNotOverridden() throws IOException {
        FreeStyleProject project = (FreeStyleProject) jenkins.getItem("Test_Slack_Plugin");
        SlackNotifier notifier = project.getPublishersList().get(SlackNotifier.class);
        SlackNotifierConfigJob slackNotifierConfigJob = notifier.getSlackNotifierConfigJob();

        assertEquals("", slackNotifierConfigJob.getTeamDomain());
        assertEquals("", slackNotifierConfigJob.getAuthToken());
        assertEquals(false, slackNotifierConfigJob.isBotUser());
        assertEquals("", slackNotifierConfigJob.getRoom());

        assertFalse(slackNotifierConfigJob.isStartNotification());
        assertTrue(slackNotifierConfigJob.isNotifySuccess());
        assertFalse(slackNotifierConfigJob.isNotifyAborted());
        assertFalse(slackNotifierConfigJob.isNotifyNotBuilt());
        assertFalse(slackNotifierConfigJob.isNotifyUnstable());
        assertFalse(slackNotifierConfigJob.isNotifyRegression());
        assertTrue(slackNotifierConfigJob.isNotifyFailure());
        assertFalse(slackNotifierConfigJob.isNotifyBackToNormal());
        assertFalse(slackNotifierConfigJob.isNotifyRepeatedFailure());
        assertFalse(slackNotifierConfigJob.isIncludeTestSummary());
        assertEquals(CommitInfoChoice.NONE, slackNotifierConfigJob.getCommitInfoChoice());
        assertFalse(slackNotifierConfigJob.isIncludeCustomMessage());
        assertEquals("", slackNotifierConfigJob.getCustomMessage());

        assertNull(project.getProperty(SlackJobProperty.class));
    }

    @Test
    @LocalData
    public void testMigrationFromNoPlugin() {
        FreeStyleProject project1 = (FreeStyleProject) jenkins.getItem("Test_01");
        assertNull(project1.getPublishersList().get(SlackNotifier.class));
        assertNull(project1.getProperty(SlackJobProperty.class));

        FreeStyleProject project2 = (FreeStyleProject) jenkins.getItem("Test_02");
        assertNull(project2.getPublishersList().get(SlackNotifier.class));
        assertNull(project2.getProperty(SlackJobProperty.class));

        MatrixProject project3 = (MatrixProject) jenkins.getItem("Test_03");
        assertNull(project3.getPublishersList().get(SlackNotifier.class));
        assertNull(project3.getProperty(SlackJobProperty.class));
    }

    @Test
    @LocalData
    public void testMigrationOfSomeJobs() throws IOException {
        // Project without Slack notifications
        FreeStyleProject project1 = (FreeStyleProject) jenkins.getItem("Test_01");
        assertNull(project1.getPublishersList().get(SlackNotifier.class));
        assertNull(project1.getProperty(SlackJobProperty.class));

        // Another project without Slack notifications
        MatrixProject project3 = (MatrixProject) jenkins.getItem("Test_03");
        assertNull(project3.getPublishersList().get(SlackNotifier.class));
        assertNull(project3.getProperty(SlackJobProperty.class));

        // Project with Slack notifications
        FreeStyleProject project2 = (FreeStyleProject) jenkins.getItem("Test_02");
        SlackNotifier notifier = project2.getPublishersList().get(SlackNotifier.class);
        assertNotNull(notifier);
        SlackNotifierConfigJob slackNotifierConfigJob = notifier.getSlackNotifierConfigJob();
        assertNotNull(slackNotifierConfigJob);


        assertEquals("", slackNotifierConfigJob.getTeamDomain());
        assertEquals("", slackNotifierConfigJob.getAuthToken());
        assertEquals(false, slackNotifierConfigJob.isBotUser());
        assertEquals("", slackNotifierConfigJob.getRoom());

        assertTrue(slackNotifierConfigJob.isStartNotification());
        assertTrue(slackNotifierConfigJob.isNotifySuccess());
        assertTrue(slackNotifierConfigJob.isNotifyAborted());
        assertTrue(slackNotifierConfigJob.isNotifyNotBuilt());
        assertTrue(slackNotifierConfigJob.isNotifyUnstable());
        assertTrue(slackNotifierConfigJob.isNotifyRegression());
        assertTrue(slackNotifierConfigJob.isNotifyFailure());
        assertTrue(slackNotifierConfigJob.isNotifyBackToNormal());
        assertTrue(slackNotifierConfigJob.isNotifyRepeatedFailure());
        assertTrue(slackNotifierConfigJob.isIncludeTestSummary());
        assertEquals(CommitInfoChoice.AUTHORS_AND_TITLES, slackNotifierConfigJob.getCommitInfoChoice());
        assertTrue(slackNotifierConfigJob.isIncludeCustomMessage());
        assertEquals("Custom message for 1.8 plugin.", slackNotifierConfigJob.getCustomMessage());

        assertNull(project2.getProperty(SlackJobProperty.class));
    }

    @Test
    @LocalData
    public void testMigrationWithNoNotifier() {
        FreeStyleProject project = (FreeStyleProject) jenkins.getItem("Test_01");
        assertNull(project.getPublishersList().get(SlackNotifier.class));
        assertNull(project.getProperty(SlackJobProperty.class));
    }
}
