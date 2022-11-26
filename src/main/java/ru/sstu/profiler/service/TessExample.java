package ru.sstu.profiler.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class TessExample {
    public void example() {

        Tesseract tesseract = new Tesseract();
        try {

            tesseract.setDatapath("Tess4J/tessdata");
            tesseract.setLanguage("rus");
            tesseract.setTessVariable("user_defined_dpi", "300");
            String text = tesseract.doOCR(new File("foto/1.jpg"));

            System.out.print(text);

            List<String> words = Arrays.asList(text.split("\n"));

            for(int i =0; i<words.size(); i++){
                System.out.println("Number [" + i + "] - " + words.get(i).toLowerCase().contains("удостоверение"));
            }


        } catch (TesseractException e) {
            e.printStackTrace();
        }

    }
}
