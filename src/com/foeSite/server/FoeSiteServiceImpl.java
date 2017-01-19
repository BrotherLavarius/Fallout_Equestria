package com.foeSite.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.foeSite.client.FoeSiteService;

public class FoeSiteServiceImpl extends RemoteServiceServlet implements FoeSiteService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}