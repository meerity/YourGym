package com.meerity.yourgym.service;

import com.meerity.yourgym.model.Contact;
import com.meerity.yourgym.repositories.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public boolean saveContact(Contact contact) {
        boolean result = false;
        contact.setStatus(ContactStatus.Open);
        Contact savedContact = contactRepository.save(contact);
        if (savedContact != null && savedContact.getContactId() > 0) {
            result = true;
        }
        contactRepository.save(contact);
        return result;
    }

    public enum ContactStatus {
        Open,
        Close
    }
}
