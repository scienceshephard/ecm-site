const categories={"gadgets":"gadgets", "laptop":"laptop","gaming":"gaming","phone":"phone","camera":"camera","headphones":"headphones","watch":"watch"};

// Function to fetch laptop images from the backend
// const url = `/api/images/gadgets/${categories.laptop}?count=${count}`;
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

// Function to load initial 8 images on page load
async function loadInitialTechImages() {
    const images = await fetchTechImages(8);
    if (!images) return;

    const imageSlider = document.querySelector(".products-card");
    imageSlider.innerHTML = ""; // Clear any existing images

    images.forEach((image) => {
        const card = document.createElement("div");
        card.classList.add("card");
        card.innerHTML = `
            <div class="products-img-card" onclick="${viewCurrentProduct(image.user.id)}">
                <img src="${image.urls.regular}" alt="Tech Image" class="fade-in">
            </div>
            <div class="products-card-content">
                ${image.alt_description || "Tech Image"}
            </div>
        `;
        // console.log(image);
        
        imageSlider.appendChild(card);
    });
    
}

// Load 8 images when page loads
document.addEventListener("DOMContentLoaded", loadInitialTechImages);


// Function to fetch and add a new image
async function addNewTechImage() {
    const newImage = await fetchTechImages(1);
    if (!newImage || newImage.length === 0) return;

    const imageSlider = document.querySelector(".products-card");

    const card = document.createElement("div");
    card.classList.add("card");
    card.innerHTML = `
        <div class="products-img-card" onclick="${viewCurrentProduct(newImage[0].user.id)}">
            <img src="${newImage[0].urls.regular}" alt="New Tech Image" class="fade-in">
        </div>
        <div class="products-card-content">
            ${newImage[0].alt_description || "Tech Image"}
        </div>
    `;

    imageSlider.appendChild(card); // Append new image to the end
}


function viewCurrentProduct(url) {
    console.log(url);
    
}