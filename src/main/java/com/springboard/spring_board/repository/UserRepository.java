package com.springboard.spring_board.repository;

import com.springboard.spring_board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User,Long> {
   User findByUsername(String username);
}
