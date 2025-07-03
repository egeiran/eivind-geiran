const carousel = document.getElementById("carousel");
const imageContainer = document.querySelector(".carousel-container");
const originalImages = [...carousel.querySelectorAll(".carousel-img")];
const indicators = document.getElementById("indicators");

let index = 0;
let autoScroll;
let restartTimeout;

// Clone first image
const firstClone = originalImages[0].cloneNode(true);
carousel.appendChild(firstClone);

const images = carousel.querySelectorAll(".carousel-img");

carousel.style.width = `${images.length * 100}%`;
images.forEach(img => img.style.width = `${100 / images.length}%`);

// Add indicator dots
for (let i = 0; i < originalImages.length; i++) {
    const dot = document.createElement("span");
    dot.classList.add("dot");
    if (i === 0) dot.classList.add("active");
    indicators.appendChild(dot);
}

function updateIndicators(i) {
    const dots = document.querySelectorAll(".dot");
    dots.forEach(dot => dot.classList.remove("active"));
    dots[i % originalImages.length].classList.add("active");
}

function showImage(i, animate = true) {
    carousel.style.transition = animate ? "transform 0.5s ease-in-out" : "none";
    carousel.style.transform = `translateX(-${i * (100 / images.length)}%)`;
    updateIndicators(i);
}

function nextImage(auto = false) {
    index++;

    showImage(index);

    // Når vi er på siste bilde (klone av første)
    if (index === images.length - 1) {
        carousel.addEventListener("transitionend", () => {
            carousel.style.transition = "none";
            index = 0;
            showImage(index, false);
        }, { once: true });
    }

    if (!auto) restartPauseTimer();
}

function prevImage() {
    index = (index - 1 + images.length) % images.length;
    showImage(index);
    restartPauseTimer();
}

function startAutoScroll() {
    clearInterval(autoScroll);
    autoScroll = setInterval(() => {
        nextImage(true); // viktig: auto = true
    }, 3000);
}

function stopAutoScroll() {
    clearInterval(autoScroll);
}

function restartPauseTimer() {
    stopAutoScroll();
    clearTimeout(restartTimeout);
    restartTimeout = setTimeout(() => {
        startAutoScroll();
    }, 10000);
}

// Init
showImage(index, false);
startAutoScroll();
