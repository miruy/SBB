package babo.yurim.sbb.user;

import lombok.Getter;

// enum : 열거 자료형
@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
