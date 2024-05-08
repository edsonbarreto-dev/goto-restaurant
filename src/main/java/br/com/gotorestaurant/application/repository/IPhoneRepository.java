package br.com.gotorestaurant.application.repository;

import br.com.gotorestaurant.application.repository.entity.PhoneEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhoneRepository extends CrudRepository<PhoneEntity, Long> {
}
