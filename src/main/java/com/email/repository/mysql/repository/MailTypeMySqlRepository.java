package com.email.repository.mysql.repository;

import com.email.repository.mysql.model.MailType;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@Repository
public interface MailTypeMySqlRepository extends CrudRepository<MailType, Integer> {
  Optional<MailType> findByNameAndIsActiveTrue(String name);
}
