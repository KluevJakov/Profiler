package ru.sstu.profiler.service;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sstu.profiler.entity.DocCategory;
import ru.sstu.profiler.entity.UserEntity;
import ru.sstu.profiler.repository.DocCategoryRepository;
import ru.sstu.profiler.repository.DocumentRepository;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CatalogizatorService {

    @Autowired
    public DocumentRepository documentRepository;
    @Autowired
    public DocCategoryRepository docCategoryRepository;


    public void parseIt(File result, UserEntity currentUser) {
        Path path = Paths.get("uploads/" + currentUser.getLogin() + "/prs/" + LocalDate.now() + "/").toAbsolutePath();

        /**
         * готовим токены и фамилии
        */
        List<DocCategory> docs = docCategoryRepository.findAll();
        ArrayList<String> surnames = new ArrayList<>();
        try {
            File file = new File("surnames.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            surnames.add(line);
            while (line != null) {
                line = reader.readLine();
                surnames.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * начинаем обработку
         */
        Tesseract tesseract = new Tesseract();
        try {
            tesseract.setDatapath("Tess4J/tessdata");
            tesseract.setLanguage("rus");

            String text = tesseract.doOCR(result);

            for (DocCategory cat : docs) {
                if (text.contains(cat.getToken().get(0).getVal())) {
                    log.info(cat.getName());
                }
            }
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }
}
