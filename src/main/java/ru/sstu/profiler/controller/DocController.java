package ru.sstu.profiler.controller;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import ru.sstu.profiler.entity.ExecResponse;
import ru.sstu.profiler.entity.UserEntity;
import ru.sstu.profiler.entity.auth.JwtRequest;
import ru.sstu.profiler.service.StorageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/doc")
@CrossOrigin(origins = "http://localhost:4200")
public class DocController {

    @Autowired
    public StorageService storageService;

    @PostMapping(value = "/loadFiles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> loadFiles(@RequestParam("files") MultipartFile[] files) {
        long startTime = System.nanoTime();
        UserEntity currentUser = (UserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file -> {
                storageService.save(file, currentUser);
            });
        } catch (Exception e) {
            System.out.println(e);
        }
        long endTime = System.nanoTime();
        double execTime = (endTime - startTime) / 1000000000d;
        return ResponseEntity.ok(new ExecResponse("Success", execTime, 0, 0, "link"));
    }
}
