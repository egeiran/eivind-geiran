let originalTracks = [];


fetch("/api/top-tracks")
    .then(res => res.json())
    .then(tracks => {
        originalTracks = tracks;
        const adjustedTracks = adjustTrackCount(tracks);
        renderTracks(adjustedTracks);
    });

function renderTracks(tracks) {
    const container = document.getElementById("tracks");
    container.innerHTML = "";
    tracks.forEach(track => {
        container.innerHTML += `
                <a href="${track.songUrl}" class="spotify-card">
                    <img src="${track.albumImageUrl}" alt="${track.name}">
                    <strong>${track.name}</strong>
                    <small>${track.artist}</small>
                </a>`;
    })
}

let resizeTimeout;
window.addEventListener("resize", () => {
    clearTimeout(resizeTimeout);
    resizeTimeout = setTimeout(() => {
        if (originalTracks.length === 0) return;
        const adjusted = adjustTrackCount(originalTracks);
        renderTracks(adjusted);
    }, 100);
});

fetch("/api/top-artists")
    .then(res => res.json())
    .then(artists => {
        const container = document.getElementById("artists");
        artists.forEach(artist => {
            container.innerHTML += `
                <a href="${artist.artistUrl}" id="top-artist">
                    <img src="${artist.imageUrl}" class="circle-img" alt="${artist.name}">
                    <strong>${artist.name}</strong>
                </a>`;
        });
    });

fetch("/api/top-artist-description").then(res => res.json()).then(data => {
    const container = document.getElementById("artists");
    container.innerHTML += `
    <p class="artist-description">${data.description}</p>
    <small class="ai-note">Denne delen er skrevet av ChatGPT, og oppdateres hver gang du refresher siden!</small>
`;
}
);

function getNumberOfColumns() {
    const containerWidth = document.querySelector('#tracks').clientWidth;
    const minCardWidth = 160; // kort + gap
    return Math.floor(containerWidth / minCardWidth);
}

function adjustTrackCount(tracks) {
    const cols = getNumberOfColumns();

    if (cols >= 5) return tracks.slice(0, 5);
    if (cols === 4) return tracks.slice(0, 4);
    if (cols === 3) return tracks.slice(0, 3);
    if (cols === 2) return tracks.slice(0, 4); // 2 rader med 2
    return tracks.slice(0, 3); // fallback (eks. mobil)
}