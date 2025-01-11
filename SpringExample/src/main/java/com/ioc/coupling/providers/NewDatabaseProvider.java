package com.ioc.coupling.providers;

import com.ioc.coupling.UserDataProvider;

public class NewDatabaseProvider implements UserDataProvider {

    @Override
    public String getUserDetails() {
        return "User details from new database";
    }
}
