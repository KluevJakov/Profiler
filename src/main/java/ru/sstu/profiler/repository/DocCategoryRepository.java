package ru.sstu.profiler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sstu.profiler.entity.DocCategory;


@Repository
public interface DocCategoryRepository extends JpaRepository<DocCategory, Long> {
}
