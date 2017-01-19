package com.foeSite.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("FoeSiteService")
public interface FoeSiteService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use FoeSiteService.App.getInstance() to access static instance of FoeSiteServiceAsync
     */
    public static class App {
        private static FoeSiteServiceAsync ourInstance = GWT.create(FoeSiteService.class);

        public static synchronized FoeSiteServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
