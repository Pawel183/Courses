package com.tight.coupling;

// A - MySQL, PostgresSQL, Oracle, etc.
// B - Web Service, MongoDB, etc.

public class UserDatabase {
    public String getUserDetails() {
        // Directly accessing the database
        return "User details from database";
    }
}
