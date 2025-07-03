package no.geiran.eivind.util;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.File;

@Component
public class ImageRenamer {

    private static final String IMAGE_FOLDER = "src/main/resources/static/EivindsVakreØyeblikkOld";

    @PostConstruct // kjøres automatisk etter Spring Boot starter
    public void cleanAndRenameImages() {
        File folder = new File(IMAGE_FOLDER);

        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("Ingen bildefiler funnet.");
            return;
        }

        // Slett filer som ikke er .jpg/.jpeg/.JPG
        for (File file : files) {
            String name = file.getName().toLowerCase();
            if (!name.matches("img\\d+\\.(jpg|jpeg|JPG)")) {
                System.out.println("Sletter ugyldig bilde: " + file.getName());
                file.delete();
            }
        }

        // Gi nytt navn IMG1.jpg, IMG2.jpg osv.
        File[] remaining = folder.listFiles((dir, name) -> name.toLowerCase().matches("img\\d{4}\\.(jpg|jpeg)"));
        if (remaining == null)
            return;

        int counter = 1;
        for (File file : remaining) {
            String newName = "IMG" + counter + ".jpg";
            File renamed = new File(folder, newName);
            if (file.renameTo(renamed)) {
                System.out.println("Endret " + file.getName() + " → " + newName);
            } else {
                System.out.println("Kunne ikke endre " + file.getName());
            }
            counter++;
        }
    }
}
