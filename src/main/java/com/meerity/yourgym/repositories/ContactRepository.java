package com.meerity.yourgym.repositories;

import com.meerity.yourgym.constants.ContactStatus;
import com.meerity.yourgym.model.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Page<Contact> findByStatus(ContactStatus status, Pageable pageable);
}
