package com.email.repository.mysql.repository;

import com.email.repository.mysql.model.User;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@Repository
public interface UserMySqlRepository extends CrudRepository<User, Long> {

  Optional<User> findByEmailAndIsActiveTrue(String emailId);

  List<User> findByEmailInAndIsActiveTrue(Collection<String> emailIds);

  default Map<String, Long> getEmailIdToUserIdMap(Collection<String> emailIds) {
    List<User> userList = findByEmailInAndIsActiveTrue(emailIds);
    return userList.stream().collect(Collectors.toMap(User::getEmail, User::getId));
  }
}
