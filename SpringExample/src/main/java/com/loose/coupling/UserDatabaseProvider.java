package com.loose.coupling;

// A - MySQL, PostgresSQL, Oracle, etc.
// B - Web Service, MongoDB, etc.

public class UserDatabaseProvider implements UserDataProvider {
    @Override
    public String getUserDetails() {
        // Directly accessing the database
        return "User details from database";
    }
}
