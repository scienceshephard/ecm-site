
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