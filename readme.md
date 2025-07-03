# Eivind Geiran – Personlig Nettside 👨‍💻

Velkommen til kildekoden for min personlige nettside!  
Denne siden er laget for å vise hvem jeg er – både som person og utvikler – og for å eksperimentere med teknologi, design og data fra Spotify.  

Nettsiden skal fungere som en digital introduksjon og et kreativt prosjekt hvor jeg kombinerer frontend og backend-teknologi med personlig stil og automatisert innhold.

## 🎯 Formål

- Presentere meg selv profesjonelt på en moderne, ærlig og leken måte  
- Vise frem erfaring, utdanning og frivillig arbeid med filtrering og interaktivitet  
- Integrere sanntidsdata fra Spotify for å vise musikk jeg faktisk hører på  
- Eksperimentere med design, animasjon og visuell identitet med streetwear-vibes  
- Lære og praktisere teknologier som Java, Spring Boot, REST API, HTML/CSS/JS og Thymeleaf  

## 🚀 Teknologier brukt

### Backend
- **Java 17**
- **Spring Boot**
- REST API-er for:
  - Autentisering mot Spotify
  - Henting av top tracks / top artist
  - Generering av AI-beskrivelse om artisten
- Thymeleaf for server-side rendering av HTML

### Frontend
- **HTML / CSS / JS**
- Tilpasset stil med blåtoner og gridsystem som gir et levende og moderne uttrykk
- Responsiv design med animasjoner og tilpasset layout for desktop og mobil
- Spotify-seksjon med grid som tilpasser seg etter skjermstørrelse

### Integrasjoner
- **Spotify Web API** – for å hente mine mest spilte sanger og artister  
- **OpenAI GPT** – genererer en ny AI-beskrivelse av artisten hver gang siden lastes  

## ✨ Funksjoner

- 🧠 **"Om meg"-seksjon** med tilpasset introduksjon og karusell med bilder  
- 🧱 **Grid-bakgrunn** som endrer seg dynamisk og gir subtil liv til nettsiden  
- 💼 **Erfaringsseksjon** med filtreringsknapper og animert visning  
- 🎧 **Spotify-seksjon** som automatisk oppdateres med musikk jeg faktisk hører på  
- 🤖 AI-generert beskrivelse av topartist som oppdateres hver gang du laster inn siden  
- 🔄 Responderer på skjermstørrelse og tilpasser innholdet automatisk  

## 🛠️ Oppsett og kjøring

### For utvikling lokalt

1. Klon repoet:
   ```bash
   git clone https://github.com/eivindgeiran/nettside.git
   cd nettside
   ```

2. Legg til `application.properties` i `src/main/resources` med din Spotify Client ID/Secret og refresh-token:
   ```properties
   spotify.client-id=...
   spotify.client-secret=...
   spotify.refresh-token=...
   ```

3. Bygg og start Spring Boot-applikasjonen:
   ```bash
   mvn spring-boot:run
   ```

4. Åpne nettleseren og gå til `http://localhost:8080`

## 📦 Videre planer

- Legge til bloggseksjon for tanker og prosjekter  
- Mulighet for besøkende å sende meldinger via kontaktskjema  
- Eventuell støtte for flere språk (norsk/engelsk)  
- Lage støtte for å vise produkter fra eget klesmerke (SYGE) direkte i nettsiden  

## 🧑‍🎨 Designvalg

- Inspirasjon fra streetwear-estetikk og minimalistiske porteføljesider  
- Bruk av blåtoner og skjeve elementer for å bryte det sterile  
- Fokus på personlighet og rytme i oppsett og bevegelse  

---

### 👋 Kontakt

Hvis du er nysgjerrig på noe, ta kontakt med meg!  
Nettsiden er et levende prosjekt – akkurat som meg selv.

– Eivind