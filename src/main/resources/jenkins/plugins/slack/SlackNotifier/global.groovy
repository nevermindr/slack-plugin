package jenkins.plugins.slack.SlackNotifier;

f = namespace('/lib/form')
c = namespace('/lib/credentials')


f.section(title: _('Global Slack Notifier Settings')) {
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
