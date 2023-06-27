package com.example.QuestMisto.services;

import java.util.UUID;

public class UUIDGenerator {
    public String getUUID() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
