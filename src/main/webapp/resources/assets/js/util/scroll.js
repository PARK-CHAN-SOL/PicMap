const footer = document.getElementById("footer");

const observer = new IntersectionObserver((items)=>{
    items.forEach((item) => {
        if(item.isIntersecting){
            console.log(item.target, "is visible!");
        }
    });
});

observer.observe(footer);