package ru.sstu.profiler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sstu.profiler.entity.Token;


@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
}
