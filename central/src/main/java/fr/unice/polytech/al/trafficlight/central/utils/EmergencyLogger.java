package fr.unice.polytech.al.trafficlight.central.utils;

import fr.unice.polytech.al.trafficlight.utils.Emergency;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Emergency logger.
 */
public class EmergencyLogger {
    private static EmergencyLogger instance;

    private List<EmergencyLog> logs = new ArrayList<>();

    private EmergencyLogger() {
    }

    public void log(Emergency emergency) {
        EmergencyLog log = new EmergencyLog();
        log.setEmergency(emergency);
        log.setWhen(new Date());
        logs.add(log);
    }

    public List<EmergencyLog> getLogs() {
        return logs;
    }

    public static EmergencyLogger getInstance() {
        if (instance == null)
            instance = new EmergencyLogger();

        return instance;
    }

    public class EmergencyLog {
        private Emergency emergency;
        private Date when;

        public Emergency getEmergency() {
            return emergency;
        }

        public void setEmergency(Emergency emergency) {
            this.emergency = emergency;
        }

        public Date getWhen() {
            return when;
        }

        public void setWhen(Date when) {
            this.when = when;
        }
    }
}
