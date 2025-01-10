package com.loose.coupling.providers;

import com.loose.coupling.UserDataProvider;

public class NewDatabaseProvider implements UserDataProvider {

    @Override
    public String getUserDetails() {
        return "User details from new database";
    }
}
