const categories={"gadgets":"gadgets", "laptop":"laptop","gaming":"gaming","phone":"phone","camera":"camera","headphones":"headphones","watch":"watch"};

function truncateWords(text, wordCount=3){
    if(!text) return "";
    const parts = text.trim().split(/\s+/);
    if(parts.length <= wordCount) return parts.join(" ");
    return parts.slice(0, wordCount).join(" ");
}

// Function to fetch laptop images from the backend
async function fetchTechImages(count) {
    try {
        const response = await fetch(`/api/images/${categories.gadgets}?count=${count}`);
        if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);

        const images = await response.json();
        return images; // Returns an array of images
    } catch (error) {
        console.error("Error fetching images:", error);
    }
}

function makeProductCard(image) {
    const card = document.createElement("div");
    card.classList.add("card");

    card.innerHTML = `
        <div class="products-img-card">
            <img src="${image.urls.regular}" alt="Tech Image">
            <div class="overlay-text" style="display:none">
                <button class="overlay-close" aria-label="Close description">×</button>
                <div class="overlay-content"></div>
            </div>
        </div>
        <div class="products-card-content">
            <span class="preview-text"></span>
            <button class="expand-btn" aria-expanded="false">...</button>
        </div>
    `;

    const preview = card.querySelector('.preview-text');
    const btn = card.querySelector('.expand-btn');
    const overlay = card.querySelector('.overlay-text');
    const overlayContent = card.querySelector('.overlay-content');
    const closeBtn = card.querySelector('.overlay-close');

    const alt = image.alt_description || "Tech Image";
    preview.textContent = truncateWords(alt, 3);
    overlayContent.textContent = alt;

    // Open overlay when expand button clicked
    btn.addEventListener('click', (e) => {
        e.stopPropagation();
        const expanded = btn.getAttribute('aria-expanded') === 'true';
        if (expanded) {
            overlay.style.display = 'none';
            btn.setAttribute('aria-expanded', 'false');
            btn.textContent = '...';
        } else {
            overlay.style.display = 'flex';
            btn.setAttribute('aria-expanded', 'true');
            btn.textContent = 'less';
        }
    });

    // Close overlay button
    closeBtn.addEventListener('click', (e) => {
        e.stopPropagation();
        overlay.style.display = 'none';
        btn.setAttribute('aria-expanded', 'false');
        btn.textContent = '...';
    });

    // Clicking card fallback behavior
    card.addEventListener("click", ()=> {
        if (typeof window.viewCurrentProduct === 'function') {
            window.viewCurrentProduct(image.user && image.user.id ? image.user.id : image.id);
        } else {
            const fallbackUrl = (image.links && image.links.html) ? image.links.html : (image.urls && image.urls.full) ? image.urls.full : null;
            if (fallbackUrl) {
                window.open(fallbackUrl, '_blank', 'noopener');
            } else {
                console.warn('No viewCurrentProduct function and no image link available for', image);
            }
        }
    });

    return card;
}

// Function to load initial 8 images on page load
async function loadInitialTechImages() {
    await new Promise(resolve => setTimeout(resolve, 3000)); // Simulate a delay for the animation
    const images = await fetchTechImages(8);
    
    if (!images) return;

    const imageSlider = document.querySelector(".products-card");
    imageSlider.innerHTML = ""; // Clear any existing images
    
    const ProdAnimation= document.querySelector(".products-card-animate");
    if (ProdAnimation) ProdAnimation.style.display = "none"; // Hide the animation overlay

    images.forEach((image) => {
        const card = makeProductCard(image);
        imageSlider.appendChild(card);
    });
    
}

// Function to fetch and add a new image
async function addNewTechImage() {
    await new Promise(resolve => setTimeout(resolve, 3000)); // Simulate a delay for the animation
    const newImage = await fetchTechImages(1);
    if (!newImage || newImage.length === 0) return;

    const imageSlider = document.querySelector(".products-card");
    const card = makeProductCard(newImage[0]);
    imageSlider.appendChild(card); // Append new image to the end
}


document.addEventListener("DOMContentLoaded", 
    ()=> {
        //loads 8 images initailly
        loadInitialTechImages();
    }
);

// Ensure a safe stub fallback so viewCurrentProduct calls won't throw
if (typeof window.viewCurrentProduct !== 'function') {
    window.viewCurrentProduct = function(productId) {
        console.log('viewCurrentProduct stub called for', productId);
        // Optional: navigate to a product page if you have one:
        // window.location.href = `/product/${productId}`;
    };
}
