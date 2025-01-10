package com.loose.coupling;

import com.loose.coupling.providers.NewDatabaseProvider;
import com.loose.coupling.providers.UserDatabaseProvider;
import com.loose.coupling.providers.WebServiceDataProvider;

public class LooseCouplingExample {
    public static void main(String[] args) {
        UserDataProvider databaseProvider = new UserDatabaseProvider();
        UserManager userManager = new UserManager(databaseProvider);
        System.out.println(userManager.getUserInfo());

        WebServiceDataProvider webServiceDataProvider = new WebServiceDataProvider();
        UserManager userManagerWithWS = new UserManager(webServiceDataProvider);
        System.out.println(userManagerWithWS.getUserInfo());

        NewDatabaseProvider newDatabaseProvider = new NewDatabaseProvider();
        UserManager userManagerWithNewDB = new UserManager(newDatabaseProvider);
        System.out.println(userManagerWithNewDB.getUserInfo());
    }
}
