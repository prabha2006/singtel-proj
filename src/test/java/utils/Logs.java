package utils;

public class Logs {

    private Logs() {
    }

    public static org.apache.log4j.Logger getLogger() {
        return org.apache.log4j.Logger.getRootLogger();
    }

    public static final void info(String message) {
        getLogger().info(">>>>> "+message);
    }

    public static final void error(String message) {
        getLogger().error(message);
    }

    public static final void trace(String message) {
        getLogger().trace(message);
    }

    public static final void fatal(String message) {
        getLogger().fatal(message);
    }

    public static final void debug(String message) {

    }

}

