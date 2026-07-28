package foodsystemmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import foodsystemmanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User>findByLoginId(String loginId);
}