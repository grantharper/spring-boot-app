package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.FormField;

@Repository
public interface FormFieldRepository extends CrudRepository<FormField, Integer> {

}
