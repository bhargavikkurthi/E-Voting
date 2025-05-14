package com.codingninjas.EVotingSystem.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.codingninjas.EVotingSystem.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findByName(String userName);

	Page<User> findAll(Pageable pageable); // pagination support

}
