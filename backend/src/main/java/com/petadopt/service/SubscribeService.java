package com.petadopt.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class SubscribeService {

    private final ConcurrentHashMap<String, String> subscribers = new ConcurrentHashMap<>();

    public String subscribe(String email) {
        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email is required");
        }

        if (subscribers.containsKey(email)) {
            throw new RuntimeException("Email already subscribed");
        }

        subscribers.put(email, "subscribed");
        return "Successfully subscribed: " + email;
    }

    public boolean isSubscribed(String email) {
        return subscribers.containsKey(email);
    }
}