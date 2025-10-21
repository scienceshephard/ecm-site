// ...existing code...
document.addEventListener('DOMContentLoaded', () => {
    const leftAr = document.getElementById("leftArr")
    const rightAr = document.getElementById("rightArr")
    const productSlider = document.querySelector(".product-image-slider")
    if (!productSlider) return;

    const scrollAmount = 250;
    const END_TOLERANCE = 5; // pixels tolerance when checking end

    productSlider.addEventListener("scroll", ()=>{
        updateButtonStates();
    });

    if (leftAr) {
        leftAr.addEventListener("click", ()=>{
            productSlider.scrollBy({left: -scrollAmount, behavior: "smooth"});
        });
    }

    if (rightAr) {
        rightAr.addEventListener("click", async ()=>{
            const atEnd = productSlider.scrollLeft + productSlider.clientWidth >= productSlider.scrollWidth - END_TOLERANCE;

            if (atEnd) {
                // fetch and append a new image (APIimages.js defines addNewTechImage as async)
                if (typeof addNewTechImage === "function") {
                    await addNewTechImage();
                    // after new item is appended, scroll to the new end
                    productSlider.scrollTo({ left: productSlider.scrollWidth, behavior: "smooth" });
                }
            } else {
                // simply scroll right by the configured amount
                productSlider.scrollBy({left: scrollAmount, behavior: "smooth"});
            }
            // update buttons state (scroll event may fire, but ensure state updated)
            setTimeout(updateButtonStates, 200);
        });
    }

    function updateButtonStates() {
        if (!leftAr || !rightAr) return;
        if(productSlider.scrollLeft <= 0){
            leftAr.disabled = true
        }else{
            leftAr.disabled = false
        }
    }
    updateButtonStates()
});
// ...existing code...