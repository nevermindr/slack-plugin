package jenkins.plugins.slack.SlackNotifier;

f = namespace('/lib/form')
c = namespace('/lib/credentials')
j = namespace('jelly:core')

f.entry(title: 'Notify Build Start') {
    f.checkbox(field: 'startNotification')
}

f.entry(title: 'Notify Aborted') {
    f.checkbox(field: 'notifyAborted')
}

f.entry(title: 'Notify Failure') {
    f.checkbox(field: 'notifyFailure')
}

f.entry(title: 'Notify Not Built') {
    f.checkbox(field: 'notifyNotBuilt')
}

f.entry(title: 'Notify Success') {
    f.checkbox(field: 'notifySuccess')
}

f.entry(title: 'Notify Unstable') {
    f.checkbox(field: 'notifyUnstable')
}

f.entry(title: 'Notify Regression') {
    f.checkbox(field: 'notifyRegression')
}

f.entry(title: 'Notify Back To Normal') {
    f.checkbox(field: 'notifyBackToNormal')
}



f.advanced() {

    f.entry(title: 'Notify Repeated Failure') {
        f.checkbox(field: 'notifyRepeatedFailure')
    }

    f.entry(title: 'Include Test Summary') {
        f.checkbox(field: 'includeTestSummary')
    }

    f.entry(title: 'Include Failed Tests') {
        f.checkbox(field: 'includeFailedTests')
    }

    f.optionalBlock(title: 'Include Custom Message',
            field: 'includeCustomMessage',
            inline: true) {
        f.entry(title: 'Custom Message',
                help: '/plugin/slack/help-projectConfig-slackCustomMessage.html') {
            f.textarea(field: 'customMessage')
        }

    }

    f.entry(title: 'Notification message includes',
            field: 'commitInfoChoice',
            description: 'What commit information to include into notification message') {
        f.select()
    }

    f.entry(field: 'baseUrl',
            name: 'baseUrl',
            title: _('Base URL'),
            help: '/plugin/slack/help-projectConfig-slackBaseUrl.html') {
        f.textbox()
    }
    f.entry(field: 'teamDomain',
            name: 'teamDomain',
            title: _('Team Subdomain'),
            help: '/plugin/slack/help-globalConfig-slackTeamDomain.html') {
        f.textbox()
    }
    f.entry(field: 'token',
            name: 'token',
            title: _('IntegrationToken'),
            help: '/plugin/slack/help-globalConfig-slackToken.html') {
        f.textbox()
    }
    f.entry(field: 'tokenCredentialId',
            name: 'tokenCredentialId',
            title: _('Integration Token Credential ID'),
            help: '/plugin/slack/help-globalConfig-tokenCredentialId.html') {
        c.select()
    }
    f.entry(field: 'botUser',
            name: 'botUser',
            title: _('Is Bot User?'),
            help: '/plugin/slack/help-globalConfig-botUser.html') {
        f.checkbox()
    }
    f.entry(field: 'room',
            name: 'room',
            title: _('Channel'),
            help: '/plugin/slack/help-globalConfig-slackRoom.html') {
        f.textbox()
    }
    f.validateButton(title: 'Test Connection',
            method: 'testConnection',
            with: 'baseUrl,teamDomain,token,tokenCredentialId,room')
}