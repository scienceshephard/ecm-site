document.addEventListener("DOMContentLoaded", () => {
    fetchImages(); // Load images on page load
});

function fetchImages() {
    fetch("/api/images/random?count=5")
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            if (!Array.isArray(data)) {
                throw new Error("Unexpected response format. Expected an array.");
            }

            const imageContainer = document.getElementById("products-img-card");
            imageContainer.innerHTML = ""; // Clear old images

            data.forEach(imageData => {
                if (imageData.urls && imageData.urls.regular) {
                    const img = document.createElement("img");
                    img.src = imageData.urls.regular;
                    img.alt = imageData.alt_description || "Unsplash Image";
                    imageContainer.appendChild(img);
                } else {
                    console.error("Invalid image data format:", imageData);
                }
            });
        })
        .catch(error => console.error("Error fetching images:", error));
}
