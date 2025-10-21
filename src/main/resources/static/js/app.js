// ...existing code...
document.addEventListener('DOMContentLoaded', () => {
    let isBtn = true;
    const btn = document.getElementById("btn");
    function ShowMobileNav(){
        if(!btn) return;
        if(isBtn){
            btn.classList.remove("bi-list")
            btn.classList.add("bi-x")
        }else{
            btn.classList.remove("bi-x")
            btn.classList.add("bi-list")
        }
        isBtn = !isBtn
        const navLinks = document.getElementById("nav-links");
        if(navLinks) navLinks.classList.toggle("active")
    }
    // expose for inline onclick in HTML
    window.ShowMobileNav = ShowMobileNav;

    // Search Button event Listeners
    const searchBtn = document.querySelector("#search-box label"); // only icon clickable
    const searchInput = document.getElementById("search-input");
    if (searchBtn && searchInput) {
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
      });
    }

    // clock
    function clock() {
        const date = new Date()
        const hours = String(date.getHours()).padStart(2, "0")
        const minutes= String(date.getMinutes()).padStart(2, "0")
        const seconds= String(date.getSeconds()).padStart(2, "0")
        const today = date.toLocaleString('default', {weekday: 'long'})
        const timeEl = document.querySelector('.time');
        if(!timeEl) return;
        timeEl.innerHTML= `
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

    // initialize AOS (if loaded)
    if (typeof AOS !== 'undefined' && AOS && AOS.init) {
        AOS.init();
    }
});
// ...existing code...