package jpabook.jpashop.member.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

	@Query("select m from Member m where m.name = :name")
	List<Member> findByName(String name);
}
