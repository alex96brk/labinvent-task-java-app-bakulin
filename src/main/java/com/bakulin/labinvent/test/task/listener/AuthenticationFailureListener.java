package com.bakulin.labinvent.test.task.listener;

import com.bakulin.labinvent.test.task.service.impl.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class AuthenticationFailureListener {
    private LoginAttemptService loginAttemptService;

    @Autowired
    public AuthenticationFailureListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent event) {
        Object principle = event.getAuthentication().getPrincipal();
        if (principle instanceof String) {
            String userName = (String) event.getAuthentication().getPrincipal();
            loginAttemptService.addUserToLoginAttemptCache(userName);
        }
    }


}
