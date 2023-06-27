package com.example.QuestMisto.models.enums;

public enum QuestType {
    GASTRO("Гастро", "gastro"),
    SCIENCE("Наука", "science"),
    ENTERTAINMENT("Розваги", "entertainment"),
    EDUCATION("Навчання", "education");

    private String name;
    private String path;

    QuestType(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
