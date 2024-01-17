package com.example.webappmanga.service.serviceInterface;

public interface CreateAndUpdateI<V,T> {
    Object create(T entity);
    T update(V key,T entity);
}
