package my.demo.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.demo.bookmark.entity.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	Privilege findByName(String name);

}
