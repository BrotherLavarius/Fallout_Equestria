package com.foeSite.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FoeSiteServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
