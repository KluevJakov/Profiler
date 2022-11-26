package ru.sstu.profiler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sstu.profiler.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findBySystemName(String systemName);
}
