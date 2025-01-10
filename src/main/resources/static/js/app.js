
var menuBtn= false

const navLinks= document.getElementById("nav-links")
function ShowMobileNav() {
    menuBtn= !menuBtn 
    if(menuBtn){
        navLinks.classList.remove("MainLinks-default")
        navLinks.classList.add("MainLinks-mobile");
    }else{
        navLinks.classList.remove("MainLinks-mobile");
        navLinks.classList.add("MainLinks-default")
    }
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

function arrowLeft(){
    console.log("arrow-left");
}
function arrowRight() {
    console.log("arrow-right");
}
function searchbox(){
    event.preventDefault()
}

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
clock();


{/* <span>Days</span>
<h1>03</h1>
</div>
<h1>:</h1>
<div class="duration">
<span>Hours</span>
<h1>07</h1>
</div>
<h1>:</h1>
<div class="duration">
<span>Minutes</span>
<h1>03</h1>
</div>
<h1>:</h1>
<div class="duration">
<span>Seconds</span>
<h1>03</h1> */}