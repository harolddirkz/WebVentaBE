package com.webventas.infraestructure.abstractServices;

import java.util.List;

public interface ICrud <T>{

    List<T> findAll();

    T create(T obj);

    T findById(int id);

    T update(T obj);

    void delete(int id);
}
