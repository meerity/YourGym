package com.meerity.yourgym.service;

import com.meerity.yourgym.model.entity.Contact;
import com.meerity.yourgym.repositories.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Page<Contact> getContactsWithOpenStatus(int pageNum, int size, String sortField, String sortDirection) {
        Sort sort = Sort.by(
                sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                sortField);
        Pageable pageable = PageRequest.of(pageNum - 1, size, sort);
        return contactRepository.findByStatus(Contact.ContactStatus.Open, pageable);

    }

    public boolean closeMessage(long msgId) {
        Contact contact;
        if (contactRepository.findById(msgId).isPresent()) {
            contact = contactRepository.findById(msgId).get();
            contact.setStatus(Contact.ContactStatus.Close);
            contactRepository.save(contact);
            return true;
        } else {
            return false;
        }
    }

    public boolean saveContact(Contact contact) {
        boolean result = false;
        contact.setStatus(Contact.ContactStatus.Open);
        Contact savedContact = contactRepository.save(contact);
        if (savedContact != null) {
            result = true;
        }
        contactRepository.save(contact);
        return result;
    }
}
