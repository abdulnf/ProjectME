package programmer.belajar.model;

import lombok.Data;
import programmer.belajar.user.Role;

@Data
public class UpdateRoleRequest {
    private Role role;
}