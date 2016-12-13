package com.example.ongteckwu.iotproj.components;

/**
 * Created by ongteckwu on 14/12/16.
 */
public class ServerSingleton {
    private Server server = new Server("server1", "test");
    private static ServerSingleton singleton = null;

    private ServerSingleton() {
    }

    public static ServerSingleton getInstance() {
        if (singleton == null) {
            singleton = new ServerSingleton();
        }
        return singleton;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
