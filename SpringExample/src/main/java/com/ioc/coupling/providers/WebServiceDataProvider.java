package com.ioc.coupling.providers;

import com.ioc.coupling.UserDataProvider;

public class WebServiceDataProvider implements UserDataProvider {

    @Override
    public String getUserDetails() {
        return "User details from web service";
    }
}
