package org.nightdivers.kupica.domain.anonymoususer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import org.nightdivers.kupica.domain.member.UserRole;
import org.nightdivers.kupica.support.domain.BaseTimeEntity;

@Getter
@Entity
@Table(name = "anonymous_user")
public class AnonymousUser extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nickname", nullable = false, length = 18, updatable = false)
    private String nickname;

    @Column(name="password", length = 64, nullable = false, updatable = false)
    private String password;

    @Column(name="ip_address", length = 20, nullable = false, updatable = false)
    private String ipAddress;

    @Enumerated(EnumType.STRING)
    @Column(name="user_role", nullable = false, updatable = false)
    private UserRole role;

    protected AnonymousUser() {}

    private AnonymousUser(String nickname,
                          String password,
                          String ipAddress,
                          UserRole role) {
        this.nickname = nickname;
        this.password = password;
        this.ipAddress = ipAddress;
        this.role = role;
    }

    public static AnonymousUser of(String nickname,
                                   String password,
                                   String ipAddress,
                                   UserRole userRole) {
        return new AnonymousUser(nickname, password, ipAddress, userRole);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AnonymousUser anonymousUser = (AnonymousUser) o;

        return this.getId() != null && Objects.equals(this.getId(), anonymousUser.getId());
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hashCode(this.getId());
    }
}
