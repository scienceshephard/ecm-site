const leftAr = document.getElementById("leftArr")
const rightAr = document.getElementById("rightArr")

const productSlider = document.querySelector(".product-image-slider")
productSlider.addEventListener("scroll", ()=>{
    const x = Math.round(productSlider.scrollLeft);
})
const scrollAmount = 250;
leftAr.addEventListener("click", ()=>{
    productSlider.scrollBy({left: -scrollAmount, behavior: "smooth"});
})

rightAr.addEventListener("click", ()=>{
    productSlider.scrollBy({left: scrollAmount, behavior: "smooth"})
})

function updateButtonStates() {
    if(productSlider.scrollLeft <= 0){
        leftAr.disabled = true
    }else{
        leftAr.disabled = false
    }

    if(productSlider.scrollLeft + productSlider.clientWidth >= productSlider.scrollWidth){
        rightAr.disabled = true
    } else{
        rightAr.disabled = false
    }
}
updateButtonStates()