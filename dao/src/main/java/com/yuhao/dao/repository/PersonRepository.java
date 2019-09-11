package com.yuhao.dao.repository;

import com.yuhao.dao.domain.model.TPerson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PersonRepository extends MongoRepository<TPerson,String> {
    TPerson findByName(String name);
    List<TPerson>  findAllByName(String name);
    @Query("{'age':?0}")
    List<TPerson> withQueryFindByAge();
}
