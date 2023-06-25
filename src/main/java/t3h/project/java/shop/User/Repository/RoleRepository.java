package t3h.project.java.shop.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t3h.project.java.shop.User.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
