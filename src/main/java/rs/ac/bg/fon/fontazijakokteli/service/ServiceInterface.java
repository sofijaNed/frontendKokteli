package rs.ac.bg.fon.fontazijakokteli.service;

import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ServiceInterface<T> {

    List<T> findAll();

    T findById(Object id) throws Exception;


    T save(T t) throws Exception;


    //T update(T t) throws Exception;


   // void deleteById(Object id) throws Exception;
}
