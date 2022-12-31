package com.example.demo.Domain.service;

import com.example.demo.Application.Exceptions.SurferStoreException;
import com.example.demo.Domain.Model.Surfer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemorySurferStore {
    ConcurrentHashMap<String,Surfer> surferMap = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, Surfer> getSurferMap() {
        return surferMap;
    }
    public void addSurfer (Surfer user){
        if (surferMap.containsKey(user.getEmailAddress()))
        {
            surferMap.replace(user.getEmailAddress(),user);
        }
        else{
            surferMap.put(user.getEmailAddress(), user);
        }
    }

    public Surfer getSurfer (String email) throws SurferStoreException {
        if (surferMap.containsKey(email))
        {
            return surferMap.get(email);
        }
        throw new SurferStoreException(USER_NOT_FOUND);
    }

    public void removeSurfer (String email){
        surferMap.remove(email);
    }

    @Override
    public String toString() {
        return "InMemorySurferStore{" +
                "surferMap=" + surferMap +
                '}';
    }

    private static final String USER_NOT_FOUND = "User does not exist.";
}
