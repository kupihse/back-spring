package com.example.backend;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Andreyko0 on 06/11/2017.
 */
@Transactional
public interface TestDao extends CrudRepository<Test, Long> {
}
