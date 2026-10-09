const categories = {
    gadgets: "gadgets",
    laptop: "laptop",
    gaming: "gaming",
    phone: "phone",
    camera: "camera",
    headphones: "headphones",
    watch: "watch"
};

function truncateWords(text, wordCount = 3) {
    if (!text) return "";
    const parts = text.trim().split(/\s+/);
    if (parts.length <= wordCount) return parts.join(" ");
    return parts.slice(0, wordCount).join(" ");
}

function capitalize(text) {
    return text ? text.charAt(0).toUpperCase() + text.slice(1) : text;
}

// Fetch images from the backend
async function fetchTechImages(count, query = categories.gadgets) {
    const params = new URLSearchParams({ query, count });
    const response = await fetch(`/api/images/gadgets?${params}`);
    if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
    return await response.json();
}

/* ---------- Image viewer (self contained) ---------- */
let viewerToken = 0; // lets us ignore late downloads from a previous click


/* ---------- Image viewer (self contained) ---------- */

function injectViewerStyles() {
    if (document.getElementById("img-viewer-styles")) return;
    const style = document.createElement("style");
    style.id = "img-viewer-styles";
    style.textContent = `
        #img-viewer {
            position: fixed;
            inset: 0;
            z-index: 5000;
            display: flex;
            align-items: center;
            justify-content: center;
            background: rgba(0, 0, 0, 0.6);
            opacity: 0;
            visibility: hidden;
            transition: opacity 0.25s ease, visibility 0.25s;
        }
        #img-viewer.open {
            opacity: 1;
            visibility: visible;
        }
        #img-viewer .viewer-frame {
            position: relative;      /* caption is positioned inside this */
            overflow: hidden;
            border-radius: 12px;
            max-width: 95vw;
            max-height: 95vh;
        }
        #img-viewer .viewer-frame img {
            display: block;
            width: auto;             /* natural width */
            height: auto;            /* natural height */
            max-width: 95vw;         /* only shrinks if bigger than the screen */
            max-height: 95vh;
            object-fit: contain;
        }
        #img-viewer .viewer-caption {
            position: absolute;
            left: 0;
            right: 0;
            bottom: 0;
            padding: 40px 14px 12px;
            color: #fff;
            background: linear-gradient(to top, rgba(0, 0, 0, 0.8), transparent);
        }
        #img-viewer .viewer-title {
            font-family: monospace;
            font-size: 1.1rem;
            margin-bottom: 3px;
            word-break: break-word;
        }
        #img-viewer .viewer-text {
            font-size: 0.9rem;
            opacity: 0.85;
            word-break: break-word;
        }
        body.img-viewer-open {
            overflow: hidden;
        }
    `;
    document.head.appendChild(style);
}

function getViewer() {
    injectViewerStyles();
    let viewer = document.getElementById("img-viewer");
    if (!viewer) {
        viewer = document.createElement("div");
        viewer.id = "img-viewer";
        viewer.innerHTML = `
            <div class="viewer-frame">
                <img alt="">
                <div class="viewer-caption">
                    <h2 class="viewer-title"></h2>
                    <p class="viewer-text"></p>
                </div>
            </div>
        `;
        viewer.addEventListener("click", closeViewer); // click anywhere to close
        document.body.appendChild(viewer);
    }
    return viewer;
}

function openViewer(image, title) {
    const viewer = getViewer();
    const img = viewer.querySelector("img");

    img.src = image.urls.regular; // already cached from the card, so it opens instantly
    img.alt = title;

    viewer.querySelector(".viewer-title").textContent = title;
    viewer.querySelector(".viewer-text").textContent =
        image.description ||
        (image.user && image.user.name ? `Photo by ${image.user.name}` : "");

    viewer.classList.add("open");
    document.body.classList.add("img-viewer-open");
}

function closeViewer() {
    const viewer = document.getElementById("img-viewer");
    if (viewer) viewer.classList.remove("open");
    document.body.classList.remove("img-viewer-open");
}

document.addEventListener("keydown", (e) => {
    if (e.key === "Escape") closeViewer();
});

/* ---------- Product cards ---------- */

function makeProductCard(image) {
    const card = document.createElement("div");
    card.classList.add("card");

    card.innerHTML = `
        <div class="products-img-card">
            <img alt="Tech image">
            <div class="overlay-text">
                <button class="overlay-close" aria-label="Close description">×</button>
                <div class="overlay-content"></div>
            </div>
        </div>
        <div class="products-card-content">
            <span class="preview-text"></span>
            <button class="expand-btn" aria-expanded="false">...</button>
        </div>
    `;

    const img = card.querySelector("img");
    const preview = card.querySelector(".preview-text");
    const btn = card.querySelector(".expand-btn");
    const overlay = card.querySelector(".overlay-text");
    const overlayContent = card.querySelector(".overlay-content");
    const closeBtn = card.querySelector(".overlay-close");

    const alt = capitalize(image.alt_description) || "Tech image";
    img.src = image.urls.regular;
    img.alt = alt;
    img.tabIndex = 0; // keyboard accessible

    img.decoding = "async";

    preview.textContent = truncateWords(alt, 3);
    overlayContent.textContent = alt;

    function setExpanded(open) {
        card.classList.toggle("expanded", open);
        btn.setAttribute("aria-expanded", String(open));
        btn.textContent = open ? "less" : "...";
    }

    btn.addEventListener("click", (e) => {
        e.stopPropagation();
        setExpanded(!card.classList.contains("expanded"));
    });

    closeBtn.addEventListener("click", (e) => {
        e.stopPropagation();
        setExpanded(false);
    });

    overlay.addEventListener("click", (e) => e.stopPropagation());

    img.addEventListener("click", (e) => {
        e.stopPropagation();
        openViewer(image, alt);
    });

    img.addEventListener("keydown", (e) => {
        if (e.key === "Enter" || e.key === " ") {
            e.preventDefault();
            openViewer(image, alt);
        }
    });

    return card;
}

// Load the initial 8 images on page load
async function loadInitialTechImages() {
    const imageSlider = document.querySelector(".products-card");
    const prodAnimation = document.querySelector(".products-card-animate");
    if (!imageSlider) return;

    try {
        const images = await fetchTechImages(8);
        if (!Array.isArray(images) || images.length === 0) {
            throw new Error("No images returned");
        }

        imageSlider.innerHTML = "";
        images.forEach((image) => imageSlider.appendChild(makeProductCard(image)));
    } catch (error) {
        console.error("Error fetching images:", error);
        imageSlider.textContent = "Could not load products. Please try again later.";
    } finally {
        if (prodAnimation) prodAnimation.style.display = "none";
    }
}

// Fetch one more image and add it to the end of the list
async function addNewTechImage() {
    const imageSlider = document.querySelector(".products-card");
    if (!imageSlider) return;

    try {
        const newImage = await fetchTechImages(1);
        if (!Array.isArray(newImage) || newImage.length === 0) return;
        imageSlider.appendChild(makeProductCard(newImage[0]));
    } catch (error) {
        console.error("Error fetching new image:", error);
    }
}

document.addEventListener("DOMContentLoaded", loadInitialTechImages);