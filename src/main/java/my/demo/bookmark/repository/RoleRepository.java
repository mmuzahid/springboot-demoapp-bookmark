package my.demo.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.demo.bookmark.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String string);

}
