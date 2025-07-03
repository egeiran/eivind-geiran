package no.geiran.eivind.util;

import no.geiran.eivind.model.Experience;

import java.util.Comparator;
import java.util.List;

public class ExperienceBuilder {

        public static List<Experience> buildAll() {
                List<Experience> experiences = List.of(
                                new Experience(
                                                "Innholdsansvarlig Gründerjakten 2025",
                                                "Start NTNU",
                                                "Frivillig",
                                                "2025",
                                                "Nå",
                                                "Ansvarlig for innhold, samarbeid og gjennomføring av Gründerjakten 2025 for Start NTNU.",
                                                "/erfaringer/start.webp"),
                                new Experience(
                                                "Datateknologi (MSc)",
                                                "NTNU",
                                                "Utdanning",
                                                "2024",
                                                "Nå",
                                                "Femårig master i Datateknologi ved NTNU i Trondheim.",
                                                "/erfaringer/ntnu.webp"),
                                new Experience(
                                                "Ishockeydommer",
                                                "Norges Ishockeyforbund",
                                                "Betalt",
                                                "2016",
                                                "2022",
                                                "Dømte aldersbestemte kamper. Ansvar for spillflyt, regler og trygg gjennomføring av kamp.",
                                                "/erfaringer/nihf.webp"),
                                new Experience(
                                                "Mentor",
                                                "MentorNorge",
                                                "Betalt",
                                                "2024",
                                                "Nå",
                                                "Hjalp elever i ungdomsskole og videregående med fag som matte og fysikk. Fokus på mestring og motivasjon.",
                                                "/erfaringer/mentornorge.webp"),
                                new Experience(
                                                "Mentor",
                                                "ENT3R Trondheim",
                                                "Betalt",
                                                "2025",
                                                "Nå",
                                                "Frivillig mentor i realfag for ungdomsskoleelever. Holdt ukentlige samlinger og jobbet med å skape læringsglede.",
                                                "/erfaringer/ent3r.webp"),
                                new Experience(
                                                "Sosialansvarlig - Arrkom",
                                                "Abakus linjeforening",
                                                "Frivillig",
                                                "2024",
                                                "Nå",
                                                "Planla og gjennomførte sosiale arrangementer for linjeforeningen Abakus. Jobbet tett i team og med eksterne aktører.",
                                                "/erfaringer/abakus.webp"),
                                new Experience(
                                                "Faddersjef",
                                                "Abakus linjeforening",
                                                "Frivillig",
                                                "2025",
                                                "2025",
                                                "Ledet fadderopplegget for nye studenter ved Datateknologi. Ansvar for logistikk, ledelse, samarbeid og kommunikasjon.",
                                                "/erfaringer/abakus.webp"),
                                new Experience(
                                                "Ferskvaremedarbeider",
                                                "Meny Bærums Verk",
                                                "Betalt",
                                                "2022",
                                                "Nå",
                                                "Jobbet i ferskvareavdelingen med kundeservice, varehåndtering, hygiene og samarbeid. Tok ansvar i travle perioder.",
                                                "/erfaringer/meny.webp"),
                                new Experience("Elev", "Sandvika VGS", "Utdanning", "2021", "2024",
                                                "Gikk på Videregående på Sandvika VGS", "erfaringer/sandvika.webp"));

                return experiences.stream()
                                .sorted(Comparator.comparing((Experience e) -> {
                                        try {
                                                return Integer.parseInt(e.getEnd());
                                        } catch (NumberFormatException ex) {
                                                return Integer.MIN_VALUE;
                                        }
                                }).reversed())
                                .sorted(Comparator.comparing((Experience e) -> {
                                        try {
                                                return Integer.parseInt(e.getStart());
                                        } catch (NumberFormatException ex) {
                                                return Integer.MIN_VALUE;
                                        }
                                }).reversed())
                                .toList();
        }
}