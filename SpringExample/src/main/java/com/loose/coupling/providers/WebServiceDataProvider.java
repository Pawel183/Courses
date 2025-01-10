package com.loose.coupling.providers;

import com.loose.coupling.UserDataProvider;

public class WebServiceDataProvider implements UserDataProvider {

    @Override
    public String getUserDetails() {
        return "User details from web service";
    }
}
