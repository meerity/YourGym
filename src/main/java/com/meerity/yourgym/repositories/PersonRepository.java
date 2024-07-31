package com.meerity.yourgym.repositories;

import com.meerity.yourgym.model.dto.ClientInfoDTO;
import com.meerity.yourgym.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByEmail(String email);

    Person findByPhoneNum(String phone);

    Person findByCardCardNumber(String number);


}
