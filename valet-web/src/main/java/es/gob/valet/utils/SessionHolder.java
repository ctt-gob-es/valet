package es.gob.valet.utils;

import java.util.concurrent.ConcurrentHashMap;

public class SessionHolder {
    // ConcurrentHashMap para manejar accesos concurrentes
    public static final ConcurrentHashMap<String, String> sessionsSAML = new ConcurrentHashMap<>();
}
