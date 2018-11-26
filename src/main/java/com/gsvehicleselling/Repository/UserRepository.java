package com.gsvehicleselling.Repository;

import com.gsvehicleselling.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByNickname(String nickname);

}