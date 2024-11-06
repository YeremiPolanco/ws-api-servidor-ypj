package sw.goku.servidor.securiry.repository.user;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Role {

    USER(Arrays.asList(Permission.READ_ALL_PRODUCT)),

    ADMIN(Arrays.asList(Permission.SAVE_ONE_USER, Permission.READ_ALL_USERS, Permission.SAVE_ONE_PRODUCT, Permission.READ_ALL_PRODUCT));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
