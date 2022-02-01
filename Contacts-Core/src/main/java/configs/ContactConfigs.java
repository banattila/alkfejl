package configs;
import hu.banattila.Logging;
import java.util.Properties;

public class ContactConfigs {

    private static Logging logger = Logging.getLogger();

    public static Logging getLogger(){
        return logger;
    }

    private static Properties props = new Properties();

    static {
        try {
            props.load(ContactConfigs.class.getResourceAsStream("/application.properties"));
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public static String getValue(String key){
        return props.getProperty(key);
    }
}
