package jenkins.plugins.slack;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import org.apache.commons.lang.StringUtils;

public class SlackNotifierConfigGlobal {
    protected String baseUrl;
    protected String teamDomain;

    protected String token;
    protected String tokenCredentialId;

    protected boolean botUser;
    protected String room;
    protected String buildServerUrl;

    @XStreamOmitField
    protected String[] roomIds;

    public String getAuthToken() {
        return token;
    }

    public void setAuthToken(String token) {
        this.token = token;
    }

    public String getAuthTokenCredentialId() {
        return tokenCredentialId;
    }

    public void setAuthTokenCredentialId(String tokenCredentialId) {
        this.tokenCredentialId = tokenCredentialId;
    }

    public SlackNotifierConfigGlobal() {

    }

    public SlackNotifierConfigGlobal(String baseUrl, String teamDomain, String token, String tokenCredentialId, boolean botUser, String room) {

        if(baseUrl != null && !baseUrl.isEmpty() && !baseUrl.endsWith("/")) {
            baseUrl += "/";
        }

        this.baseUrl = baseUrl;
        this.teamDomain = teamDomain;
        this.token = token;
        this.tokenCredentialId = StringUtils.trim(tokenCredentialId);
        this.botUser = botUser;
        this.room = room;
        this.roomIds = room.split("[,; ]+");
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getTeamDomain() {
        return teamDomain;
    }

    public boolean isBotUser() {
        return botUser;
    }

    public String getRoom() {
        return room;
    }

    public String[] getRoomIds() {
        return roomIds;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setTeamDomain(String teamDomain) {
        this.teamDomain = teamDomain;
    }

    public void setBotUser(boolean botUser) {
        this.botUser = botUser;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setRoomIds(String[] roomIds) {
        this.roomIds = roomIds;
    }

    public String getBuildServerUrl() {
        return buildServerUrl;
    }

    public void setBuildServerUrl(String buildServerUrl) {
        this.buildServerUrl = buildServerUrl;
    }

    public static class SlackNotifierConfigGlobalConverter extends ReflectionConverter {
        public SlackNotifierConfigGlobalConverter(Mapper mapper, ReflectionProvider reflectionProvider) {
            super(mapper, reflectionProvider);
        }

        protected Object instantiateNewInstance(HierarchicalStreamReader reader, UnmarshallingContext context) {
            return this.reflectionProvider.newInstance(SlackNotifierConfigGlobal.class);
        }

        public void marshal(Object source, HierarchicalStreamWriter writer,
                            MarshallingContext context) {

            super.marshal(((SlackNotifier.DescriptorImpl)source).getSlackNotifierConfigGlobal(), writer, context);
        }

        public Object unmarshal(HierarchicalStreamReader reader,
                                UnmarshallingContext context) {

            SlackNotifierConfigGlobal slackNotifierConfigGlobal = (SlackNotifierConfigGlobal) super.unmarshal(reader, context);

            SlackNotifier.DescriptorImpl descriptor = (SlackNotifier.DescriptorImpl) context.currentObject();
            descriptor.setSlackNotifierConfigGlobal(slackNotifierConfigGlobal);

            return descriptor;
        }

        public boolean canConvert(Class type) {
            return type.equals(SlackNotifier.DescriptorImpl.class);
        }
    }

}
