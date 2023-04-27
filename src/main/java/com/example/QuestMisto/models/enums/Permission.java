package com.example.QuestMisto.models.enums;

public enum Permission {
    READ_QUESTS("read:quests"),
    WRITE_QUESTS("write:quests");
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
