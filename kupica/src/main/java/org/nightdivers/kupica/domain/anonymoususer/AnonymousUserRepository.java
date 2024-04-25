package org.nightdivers.kupica.domain.anonymoususer;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnonymousUserRepository extends JpaRepository<AnonymousUser, Long> {
    List<AnonymousUser> findAllByNickname(String nickname);

    List<AnonymousUser> findAllByIpAddress(String ipAddress);

    List<AnonymousUser> findAllByNicknameAndIpAddress(String nickname, String ipAddress);
}
