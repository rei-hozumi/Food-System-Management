package foodsystemmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import foodsystemmanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}