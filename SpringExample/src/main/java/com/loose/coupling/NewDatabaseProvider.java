package com.loose.coupling;

public class NewDatabaseProvider implements UserDataProvider {

    @Override
    public String getUserDetails() {
        return "User details from new database";
    }
}
