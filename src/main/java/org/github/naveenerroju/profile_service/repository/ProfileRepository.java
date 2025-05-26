package org.github.naveenerroju.profile_service.repository;

import org.github.naveenerroju.profile_service.entity.ProfileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<ProfileEntity, String> {}