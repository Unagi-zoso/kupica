package org.nightdivers.kupica.domain.member;

import static org.nightdivers.kupica.domain.member.MemberValidator.validateEmailAddress;
import static org.nightdivers.kupica.domain.member.MemberValidator.validateNickname;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import org.nightdivers.kupica.support.domain.ModifiableBaseEntity;

@Getter
@Entity
@Table(name = "member")
@SequenceGenerator(
        name = "member_sequence_generator",
        sequenceName = "member_sequence",
        allocationSize = 1
)
public class Member extends ModifiableBaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_sequence_generator")
    @Column(columnDefinition = "NUMERIC(19,0)")
    private Long id;

    @Column(nullable = false, unique = true, length = 18)
    private String nickname;

    @Column(name = "email_address", nullable = false, updatable = false, unique = true)
    private String emailAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_login_type", nullable = false, updatable = false, length = 20)
    private SocialLoginType socialLoginType;

    protected Member() {}

    private Member(String nickname,
                   String emailAddress,
                   UserRole role,
                   SocialLoginType socialLoginType) {
        this.nickname = validateNickname(nickname);
        this.emailAddress = validateEmailAddress(emailAddress);
        this.role = role;
        this.socialLoginType = socialLoginType;
    }

    public static Member of(String nickname,
                            String emailAddress,
                            UserRole role,
                            SocialLoginType socialLoginType) {
        return new Member(nickname, emailAddress, role, socialLoginType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Member member = (Member) o;

        return this.getId() != null && Objects.equals(this.getId(), member.getId());
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hashCode(this.getId());
    }

    public void changeNickname(String newNickname) {
        this.nickname = newNickname;
    }
}
