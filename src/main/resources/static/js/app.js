
let isBtn = true
const btn = document.getElementById("btn");
function ShowMobileNav(){
    if(isBtn){
        btn.classList.remove("bi-list")
        btn.classList.add("bi-x")
    }else{
        btn.classList.remove("bi-x")
        btn.classList.add("bi-list")
    }
    isBtn = !isBtn
    document.getElementById("nav-links").classList.toggle("active")
}
var userdiv = false
const user = document.getElementById("user")

function showUserdiv(){
    userdiv = !userdiv
    if(userdiv){
user.classList.remove("User-mobile")
        user.classList.add("User-default")
    }else{
        user.classList.remove("User-default")
        user.classList.add("User-mobile")
    }
    console.log(userdiv);
    
}
//Search Button event Listeners
const searchBtn = document.querySelector("#search-box label"); // only icon clickable
const searchInput = document.getElementById("search-input");

searchBtn.addEventListener("click", (e) => {
  e.preventDefault(); // prevent label focusing default
  searchInput.classList.toggle("show");

  if (searchInput.classList.contains("show")) {
    searchInput.focus();
  } else {
    searchInput.blur();
  }
});

document.addEventListener("click", (e) => {
    if(!searchBtn.contains(e.target) && !searchInput.contains(e.target)){
        searchInput.classList.remove("show");
        searchInput.blur();
    }
})

//clock
const days= ["Sunday", "Monday", "Tuesday", "Wednessday","Thursday", "Friday", "Saturday"]

function clock() {

    const date = new Date()
    const hours = String(date.getHours()).padStart(2, "0")
    // const hours = date.getHours() < 10 ? "0" : date.getHours()
    const minutes= String(date.getMinutes()).padStart(2, "0")
    const seconds= String(date.getSeconds()).padStart(2, "0")
    const today = date.toLocaleString('default', {weekday: 'long'})
    // document.querySelector('.day').textContent = date.toLocaleString('default', {weekday: 'long'})
    document.querySelector('.time').innerHTML= `
        <div class="duration">
            <h1>${today}</h1>
        </div>
        <div class="duration"> 
            <p>Hour</p>
            <h1>${hours}</h1>
        </div> 
        <h1>:<h1>
        <div class="duration">
            <p>Minute</p>
            <h1>${minutes}</h1>
        </div> 
        <h1>:<h1>
        <div class="duration">
            <p>Seconds</p>  
            <h1>${seconds}</h1>
        </div> 
    ` 
}
setInterval(clock, 1000)
clock()




async function fetchApiKey() {
    try{
        let reposnse = await fetch('/api/key');
        let data = await reposnse.json();
        return data.accessKey;
    }catch(error){
        console.log("Error fetching the API key: "+error);
        return null;
        
    }
}

async function fetchRandomImages() {
    let apiKey = await fetchApiKey();
    if(!apiKey){
        console.error("API key not found");
        return;
    }
    try{
        let reponse = await fetch(`https://api.unsplash.com/photos/random?count=5&client_id=${apiKey}`);
        let data = await reponse.json();
        let products = document.getElementById("products-img-card");
        products.innerHTML="";
        data.forEach(img => {
            let imageElement = document.createElement('img');
            imageElement.src = img.urls.small;
            imageElement.alt = img.alt_description || "Unsplash Image";
            imageContainer.appendChild(imageElement);
        });
    } catch (error) {
        console.error("Error fetching images:", error);
        
    }
}
AOS.init()