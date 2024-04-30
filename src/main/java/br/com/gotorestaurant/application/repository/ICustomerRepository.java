package br.com.gotorestaurant.application.repository;

import br.com.gotorestaurant.application.repository.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends CrudRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByName(String name);
    Optional<CustomerEntity> findByDocument(String document);
    Optional<CustomerEntity> findByEmail(String email);
}
