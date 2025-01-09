package com.loose.coupling;

public class UserManager {

    private final UserDataProvider userDatabaseProvider;

    public UserManager(UserDataProvider userDatabaseProvider) {
        this.userDatabaseProvider = userDatabaseProvider;
    }

    public String getUserInfo() {
        return userDatabaseProvider.getUserDetails();
    }
}
