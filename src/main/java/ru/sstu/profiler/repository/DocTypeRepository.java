package ru.sstu.profiler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sstu.profiler.entity.DocType;


@Repository
public interface DocTypeRepository extends JpaRepository<DocType, Long> {
}
