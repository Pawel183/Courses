package com.loose.coupling.providers;

// A - MySQL, PostgresSQL, Oracle, etc.
// B - Web Service, MongoDB, etc.

import com.loose.coupling.UserDataProvider;

public class UserDatabaseProvider implements UserDataProvider {
    @Override
    public String getUserDetails() {
        // Directly accessing the database
        return "User details from database";
    }
}
