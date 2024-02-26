package com.reservation_api.repository;

import com.reservation_api.model.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Integer> {

}
