package ru.sstu.profiler.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.sstu.profiler.entity.UserEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.stream.Stream;

@Service
@Slf4j
public class StorageService {
    private final Path root = Paths.get("uploads");

    public void init() throws IOException {
        Files.createDirectory(root);
    }

    public void save(MultipartFile file, UserEntity currentUser) {
        Path path = Paths.get("uploads/" + currentUser.getLogin() + "/raw/" + LocalDate.now() + "/").toAbsolutePath();
        try {
            if (!Files.exists(path)) {
                log.info("create directory: " + path);
                Files.createDirectories(path);
            }
            log.info("Try to save file " + file.getOriginalFilename());
            Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Resource load(String filename) throws MalformedURLException {
        Path file = root.resolve(filename);
        Resource resource = new UrlResource(file.toUri());

        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Could not read the file!");
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    public Stream<Path> loadAll() throws IOException {
        return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    }
}
