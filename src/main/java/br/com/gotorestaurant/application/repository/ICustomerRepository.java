package br.com.gotorestaurant.application.repository;

import br.com.gotorestaurant.application.repository.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends CrudRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByName(String name);
    Optional<CustomerEntity> findByDocument(String document);
    Optional<CustomerEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Customer c set c = :customer")
    void updateCustomer(CustomerEntity customer);
}
