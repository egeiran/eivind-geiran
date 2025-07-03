package no.geiran.eivind.model;

public class Experience {
    private String title;
    private String organization;
    private String type; // f.eks. "Betalt", "Frivillig", "Utdanning"
    private String start;
    private String end;
    private String description;
    private String imagePath;

    public Experience(String title, String organization, String type, String start, String end, String description,
            String imagePath) {
        this.title = title;
        this.organization = organization;
        this.type = type;
        this.start = start;
        this.end = end;
        this.description = description;
        this.imagePath = imagePath;
    }

    // Gettere og settere
    public String getTitle() {
        return title;
    }

    public String getOrganization() {
        return organization;
    }

    public String getType() {
        return type;
    }

    public String getPeriod() {
        if (start == end) {
            return start;
        }
        return start + " - " + end;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "title='" + title + '\'' +
                ", organization='" + organization + '\'' +
                ", type='" + type + '\'' +
                ", period='" + getPeriod() + '\'' +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
