package org.nightdivers.kupica.domain.member;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByEmailAddress(String emailAddress);

    List<Member> findByRole(UserRole role);

    boolean existsByNickname(String nickname);

    boolean existsByEmailAddress(String emailAddress);
}
