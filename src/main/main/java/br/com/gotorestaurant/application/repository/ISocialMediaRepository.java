package br.com.gotorestaurant.application.repository;

import br.com.gotorestaurant.application.repository.entity.SocialMediaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISocialMediaRepository extends CrudRepository<SocialMediaEntity, Long> {
}
