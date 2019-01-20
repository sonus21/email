package com.email.repository.redis;

import org.springframework.stereotype.Repository;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@Repository
public class UserAccessTokenRedisRepository extends AbstractRedisRepository<Long, String> {}
