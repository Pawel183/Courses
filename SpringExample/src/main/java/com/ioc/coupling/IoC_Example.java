package com.ioc.coupling;

import com.ioc.coupling.providers.NewDatabaseProvider;
import com.ioc.coupling.providers.UserDatabaseProvider;
import com.ioc.coupling.providers.WebServiceDataProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IoC_Example {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationIoC.xml");

        UserManager userManager = (UserManager) context.getBean("userManagerWithUserDataProvider");
        System.out.println(userManager.getUserInfo());

        UserManager userManagerWithNewDB = (UserManager) context.getBean("userManagerWithNewDataProvider");
        System.out.println(userManagerWithNewDB.getUserInfo());

        UserManager userManagerWithWS = (UserManager) context.getBean("userManagerWithWebServiceDataProvider");
        System.out.println(userManagerWithWS.getUserInfo());
    }
}
