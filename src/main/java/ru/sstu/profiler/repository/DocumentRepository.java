package ru.sstu.profiler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sstu.profiler.entity.Document;


@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
