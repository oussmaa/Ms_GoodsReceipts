package com.example.ms_goodsreceipts.Exception;

public class EntityNotFoundExceptions extends RuntimeException {
    public EntityNotFoundExceptions(String entityName) {
        super(entityName );
    }
}
