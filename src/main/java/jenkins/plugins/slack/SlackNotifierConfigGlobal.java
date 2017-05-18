package jenkins.plugins.slack;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import hudson.EnvVars;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.logging.Logger;

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
    protected String sendAs;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenCredentialId() {
        return tokenCredentialId;
    }

    public void setTokenCredentialId(String tokenCredentialId) {
        this.tokenCredentialId = tokenCredentialId;
    }

    public SlackNotifierConfigGlobal() {

    }

    public SlackNotifierConfigGlobal(String baseUrl, String teamDomain, String token, String tokenCredentialId, boolean botUser, String room) {
        this(baseUrl, teamDomain, token, tokenCredentialId, botUser, room, "");
    }

    public SlackNotifierConfigGlobal(String baseUrl, String teamDomain, String token, String tokenCredentialId, boolean botUser, String room, String sendAs) {
        this.baseUrl = baseUrl;
        this.teamDomain = teamDomain;
        this.token = token;
        this.tokenCredentialId = StringUtils.trim(tokenCredentialId);
        this.botUser = botUser;
        this.room = room;
        this.sendAs = sendAs;

        this.checkData();
    }

    public void checkData() {
        setBaseUrl(baseUrl);
        setRoom(room);

        this.roomIds = room!=null?room.split("[,; ]+"):new String[]{};
    }

    public void copyIfEmpty(SlackNotifierConfigGlobal slackNotifierConfigGlobal) {
        if (StringUtils.isEmpty(teamDomain)) {
            teamDomain = slackNotifierConfigGlobal.getTeamDomain();
        }

        if (StringUtils.isEmpty(baseUrl)) {
            baseUrl = slackNotifierConfigGlobal.getBaseUrl();
        }

        if (StringUtils.isEmpty(token)) {
            token = slackNotifierConfigGlobal.getToken();
            botUser = slackNotifierConfigGlobal.isBotUser();
        }

        if (StringUtils.isEmpty(tokenCredentialId)) {
            tokenCredentialId = slackNotifierConfigGlobal.getTokenCredentialId();
        }

        if (StringUtils.isEmpty(room)) {
            setRoom(slackNotifierConfigGlobal.getRoom());
        }
    }

    public void copyFromEnvironment(EnvVars env) {
        baseUrl = env.expand(baseUrl);
        teamDomain = env.expand(teamDomain);
        //FIXME: get both for compatibility
//        token = env.expand(authToken);
//        tokenCredentialId = env.expand(authTokenCredentialId);
        room = env.expand(room);
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
        if(this.baseUrl != null && !this.baseUrl.isEmpty() && !this.baseUrl.endsWith("/")) {
            this.baseUrl += "/";
        }
    }

    public void setTeamDomain(String teamDomain) {
        this.teamDomain = teamDomain;
    }

    public void setBotUser(boolean botUser) {
        this.botUser = botUser;
    }

    public void setRoom(String room) {
        this.room = room;
        this.roomIds = room!=null?room.split("[,; ]+"):new String[]{};

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

    public String getSendAs() {
        return sendAs;
    }

    public void setSendAs(String sendAs) {
        this.sendAs = sendAs;
    }

    public static class SlackNotifierConfigGlobalConverter extends ReflectionConverter {
        private static final Logger logger = Logger.getLogger(SlackNotifierConfigGlobalConverter.class.getName());

        public SlackNotifierConfigGlobalConverter(Mapper mapper, ReflectionProvider reflectionProvider) {
            super(mapper, reflectionProvider);
        }

        protected Object instantiateNewInstance(HierarchicalStreamReader reader, UnmarshallingContext context) {
            return this.reflectionProvider.newInstance(SlackNotifierConfigGlobal.class);
        }

        public void marshal(Object source, HierarchicalStreamWriter writer,
                            MarshallingContext context) {

            SlackNotifierConfigGlobal slackNotifierConfigGlobal = ((SlackNotifier.DescriptorImpl)source).getSlackNotifierConfigGlobal();

            logger.fine(String.format("token: %s", slackNotifierConfigGlobal.getToken()));
            logger.fine(String.format("tokenCredentialId: %s", slackNotifierConfigGlobal.getTokenCredentialId()));

            logger.fine(String.format("baseURL: %s", slackNotifierConfigGlobal.getBaseUrl()));
            logger.fine(String.format("room: %s", slackNotifierConfigGlobal.getRoom()));

            super.marshal(slackNotifierConfigGlobal, writer, context);
        }

        public Object unmarshal(HierarchicalStreamReader reader,
                                UnmarshallingContext context) {

            SlackNotifierConfigGlobal slackNotifierConfigGlobal = (SlackNotifierConfigGlobal) super.unmarshal(reader, context);

            logger.fine(String.format("token: %s", slackNotifierConfigGlobal.getToken()));
            logger.fine(String.format("baseURL: %s", slackNotifierConfigGlobal.getBaseUrl()));
            logger.fine(String.format("room: %s", slackNotifierConfigGlobal.getRoom()));

            SlackNotifier.DescriptorImpl descriptor = (SlackNotifier.DescriptorImpl) context.currentObject();
            descriptor.setSlackNotifierConfigGlobal(slackNotifierConfigGlobal);

            return descriptor;
        }

        public boolean canConvert(Class type) {
            return type.equals(SlackNotifier.DescriptorImpl.class);
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append(this.getClass().getSuperclass().getName());
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in superclass
        Field[] parentFields = this.getClass().getSuperclass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : parentFields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }

        result.append("}");

        result.append("}");

        return result.toString();
    }

}
