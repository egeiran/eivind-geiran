package no.geiran.eivind;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.File;
import java.util.List;
import no.geiran.eivind.model.Experience;
import no.geiran.eivind.util.ExperienceBuilder;

@Controller
public class WebController {

    @GetMapping("/")
    public String home(Model model) {
        File folder = new File("src/main/resources/static/EivindsVakreØyeblikk");
        File[] files = folder.listFiles((dir, name) -> name.matches("IMG\\d+\\.webp"));

        List<Experience> all = ExperienceBuilder.buildAll();
        int numImages = files != null ? files.length : 0;

        model.addAttribute("allExperiences", all);
        model.addAttribute("numImages", numImages);
        model.addAttribute("name", "Eivind Geiran");
        model.addAttribute("description", "Datateknologi-student ved NTNU som bygger stilige nettsider i Java.");
        return "index";

    }

    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("Er jeg best", 1);
        return "error";
    }
}