
var menuBtn= true

const menu=document.getElementById("menubtn")
const navLinks= document.getElementById("nav-links")

menu.addEventListener('click', function(event){
    event.preventDefault();
    menuBtn= !menuBtn 
    if(menuBtn){
        navLinks.classList.remove("MainLinks-default")
        console.log(menuBtn);
        navLinks.classList.add("MainLinks-mobile");
    }else{
        navLinks.classList.remove("MainLinks-mobile");
        navLinks.classList.add("MainLinks-default")
    }
})

function arrowLeft(){
    console.log("arrow-left");
}
function arrowRight() {
    console.log("arrow-right");
}