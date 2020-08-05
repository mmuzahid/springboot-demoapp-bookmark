package my.demo.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.demo.bookmark.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
