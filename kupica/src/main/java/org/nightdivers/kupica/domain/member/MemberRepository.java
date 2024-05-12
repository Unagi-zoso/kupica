package org.nightdivers.kupica.domain.member;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByIdAndErasedFlagIsFalse(Long id);

    Optional<Member> findByNicknameAndErasedFlagIsFalse(String nickname);

    Optional<Member> findByEmailAddressAndErasedFlagIsFalse(String emailAddress);

    List<Member> findByRoleAndErasedFlagIsFalse(UserRole role);

    boolean existsByIdAndErasedFlagIsFalse(long id);

    boolean existsByNicknameAndErasedFlagIsFalse(String nickname);

    boolean existsByEmailAddressAndErasedFlagIsFalse(String emailAddress);
}
