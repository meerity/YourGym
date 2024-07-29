package com.meerity.yourgym.repositories;

import com.meerity.yourgym.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByEmail(String email);

    Person findByPhoneNum(String phone);

    Person findByCardCardNumber(String number);
}
