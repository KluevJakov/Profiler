package ru.sstu.profiler.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.sstu.profiler.entity.DocCategory;
import ru.sstu.profiler.entity.UserEntity;
import ru.sstu.profiler.repository.DocCategoryRepository;
import ru.sstu.profiler.repository.DocumentRepository;
import ru.sstu.profiler.repository.TokenRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
public class CategoryService {
    @Autowired
    public DocCategoryRepository docCategoryRepository;

    public void save (DocCategory category) {
        if (category.getToken() != null && category.getToken().size() != 0) {
            log.info("try to save: " + category);
            docCategoryRepository.save(category);
        }
    }

    public List<DocCategory> getAll() {
        return docCategoryRepository.findAll();
    }

    public void delete(long id) {
        docCategoryRepository.deleteById(id);
    }
}
