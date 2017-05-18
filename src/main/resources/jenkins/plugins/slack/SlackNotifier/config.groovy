package jenkins.plugins.slack.SlackNotifier

import jenkins.plugins.slack.SlackNotifierConfigJob;

f = namespace('/lib/form')
c = namespace('/lib/credentials')
SlackNotifierConfigJob slackNotifierConfigJob = instance.getSlackNotifierConfigJob();

f.entry(field: 'startNotification',
        title: 'Notify Build Start') {
    f.checkbox(checked: slackNotifierConfigJob.isStartNotification())
}

f.entry(field: 'notifyAborted',
        title: 'Notify Aborted') {
    f.checkbox(checked: slackNotifierConfigJob.isNotifyAborted())
}

f.entry(field: 'notifyFailure',
        title: 'Notify Failure') {
    f.checkbox(checked: slackNotifierConfigJob.isNotifyFailure())
}

f.entry(field: 'notifyNotBuilt',
        title: 'Notify Not Built') {
    f.checkbox(checked: slackNotifierConfigJob.isNotifyNotBuilt())
}

f.entry(field: 'notifySuccess',
        title: 'Notify Success') {
    f.checkbox(checked: slackNotifierConfigJob.isNotifySuccess())
}

f.entry(field: 'notifyUnstable',
        title: 'Notify Unstable') {
    f.checkbox(checked: slackNotifierConfigJob.isNotifyUnstable())
}

f.entry(field: 'notifyRegression',
        title: 'Notify Regression') {
    f.checkbox(checked: slackNotifierConfigJob.isNotifyRegression())
}

f.entry(field: 'notifyBackToNormal',
        title: 'Notify Back To Normal') {
    f.checkbox(checked: slackNotifierConfigJob.isNotifyBackToNormal())
}



f.advanced() {

    f.entry(field: 'notifyRepeatedFailure',
            title: 'Notify Repeated Failure') {
        f.checkbox(checked: slackNotifierConfigJob.isNotifyRepeatedFailure())
    }

    f.entry(field: 'includeTestSummary',
            title: 'Include Test Summary') {
        f.checkbox(checked: slackNotifierConfigJob.isIncludeTestSummary())
    }

    f.entry(field: 'includeFailedTests',
            title: 'Include Failed Tests') {
        f.checkbox(checked: slackNotifierConfigJob.isIncludeFailedTests())
    }

    f.optionalBlock(title: 'Include Custom Message',
            field: 'includeCustomMessage',
            inline: true,
            checked: slackNotifierConfigJob.isIncludeCustomMessage()) {
        f.entry(field: 'customMessage',
                title: 'Custom Message',
                help: '/plugin/slack/help-projectConfig-slackCustomMessage.html') {
            f.textarea(value: slackNotifierConfigJob.getCustomMessage())
        }
    }

    f.entry(title: 'Notification message includes',
            field: 'commitInfoChoice',
            description: 'What commit information to include into notification message') {
        f.select(value: slackNotifierConfigJob.getCommitInfoChoice())
    }

    f.entry(field: 'baseUrl',
            name: 'baseUrl',
            title: _('Base URL'),
            help: '/plugin/slack/help-projectConfig-slackBaseUrl.html') {

        f.textbox(value: slackNotifierConfigJob.getBaseUrl())
    }
    f.entry(field: 'teamDomain',
            name: 'teamDomain',
            title: _('Team Subdomain'),
            help: '/plugin/slack/help-globalConfig-slackTeamDomain.html') {

        f.textbox(value: slackNotifierConfigJob.getTeamDomain())
    }
    f.entry(field: 'token',
            name: 'token',
            title: _('IntegrationToken'),
            help: '/plugin/slack/help-globalConfig-slackToken.html') {
        f.textbox(value: slackNotifierConfigJob.getToken())
    }
    f.entry(field: 'tokenCredentialId',
            name: 'tokenCredentialId',
            title: _('Integration Token Credential ID'),
            help: '/plugin/slack/help-globalConfig-tokenCredentialId.html') {
        c.select(value: slackNotifierConfigJob.getTokenCredentialId())
    }
    f.entry(field: 'botUser',
            name: 'botUser',
            title: _('Is Bot User?'),
            help: '/plugin/slack/help-globalConfig-botUser.html') {
        f.checkbox(checked: slackNotifierConfigJob.isBotUser())
    }
    f.entry(field: 'room',
            name: 'room',
            title: _('Channel'),
            help: '/plugin/slack/help-globalConfig-slackRoom.html') {
        f.textbox(value: slackNotifierConfigJob.getRoom())
    }
    f.validateButton(title: 'Test Connection',
            method: 'testConnection',
            with: 'baseUrl,teamDomain,token,tokenCredentialId,room')
}