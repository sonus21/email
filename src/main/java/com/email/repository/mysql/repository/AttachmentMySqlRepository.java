package com.email.repository.mysql.repository;

import com.email.repository.mysql.model.Attachment;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@Repository
public interface AttachmentMySqlRepository extends CrudRepository<Attachment, Long> {

  List<Attachment> findByEmailIdAndIsActiveTrueOrderByAttachmentOrderAsc(Long emailId);
}
