package ru.sstu.profiler.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FilenameUtils;


public class TessExample {
    public static void main(String[] args) throws IOException {

        List<Path> folder = Files.walk(Paths.get("foto/"))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
        String[] fold = folder.toString().replace("[", "").replace("]", "").split(",");

        ArrayList<String> docs = new ArrayList<>();
        ArrayList<String> surnames = new ArrayList<>();

        docs.add("удостоверение");
        docs.add("страховая");
        docs.add("страховое");

        try {
            File file = new File("surnames.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                surnames.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Tesseract tesseract = new Tesseract();
        try {

            tesseract.setDatapath("Tess4J/tessdata");
            tesseract.setLanguage("rus");

            for(int f=0; f<fold.length; f++) {

                fold[f] = fold[f].replace(" ", "");
                String text = tesseract.doOCR(new File(fold[f]));

                //System.out.print(text);

                ArrayList<String> words = new ArrayList<>(Arrays.asList(text.split("\n")));

                String fileName = "";
                for (int i = 0; i < words.size(); i++) {
                    for(int j=0; j < docs.size(); j++){
                        if (words.get(i).toLowerCase().contains(docs.get(j).toLowerCase())) {

                            for(int q = 0; q < 2; q++) {
                                if (words.get(i).toLowerCase().contains("страхово") || words.get(i).toLowerCase().contains("страхован")) {
                                    words.remove(i);
                                }
                            }

                            loop:
                            for (int h = 0; h < words.size(); h++) {
                                for (int k = 0; k < surnames.size() - 1; k++) {

                                    if (words.get(h).toLowerCase().contains(surnames.get(k).toLowerCase())) {
                                        System.out.println("Word - " + words.get(h) + ": Surname - " + surnames.get(k));
                                        fileName = surnames.get(k);
                                        break loop;
                                    }
                                }
                            }

                            System.out.println(docs.get(j));
                            /*
                            new File("documents/"+docs.get(j)).mkdirs();
                            new File("documents/"+docs.get(j)+"/"+fileName).mkdirs();
                            Path now = Paths.get(fold[f]), neww = Paths.get("documents/" + docs.get(j) + "/" + fileName + "/" + fileName +".jpg");
                            */

                            new File("documents/"+fileName).mkdirs();
                            new File("documents/"+fileName+"/"+docs.get(j)).mkdirs();
                            Path now = Paths.get(fold[f]), neww = Paths.get("documents/" + fileName + "/" + docs.get(j)+ "/" + fileName +".jpg");

                            Files.move(now, neww);
                        }
                    }
                }

                /*loop:
                for (int i = 0; i < words.size(); i++) {
                    for (int j = 0; j < surnames.size() - 1; j++) {
                        if (words.get(i).toLowerCase().contains(surnames.get(j).toLowerCase())) {
                            //new File("C:\\Users\\Aorus\\Desktop\\Test\\src\\володин").mkdirs();
                            System.out.println("Word - " + words.get(i) + ": Surname - " + surnames.get(j));
                            break loop;
                        }
                    }

                }*/

            }


        } catch (TesseractException e) {
            e.printStackTrace();
        }


    }

}
