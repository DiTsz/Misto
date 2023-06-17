package com.example.QuestMisto.exceptions;

public class DuplicateEntityException extends Exception{
    public DuplicateEntityException(String error){
        super(error);
    }
}
