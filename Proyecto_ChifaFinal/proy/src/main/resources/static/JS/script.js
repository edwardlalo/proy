//------------ Desplegar la barra de opciones----------------------
document.getElementById("menuToggle").addEventListener("click", function () {
    var navLinks = document.getElementById("navLinks");
    navLinks.classList.toggle("active");
  });
  
  
  
  //------------- Funcion del Slider -------------------------------
  let currentSlide = 0;
  const slides = document.querySelectorAll(".slide");
  
  function showSlide(index) {
    slides.forEach((slide, i) => {
      slide.classList.remove("active");
      if (i === index) {
        slide.classList.add("active");
      }
    });
  }
  
  function nextSlide() {
    currentSlide = (currentSlide + 1) % slides.length;
    showSlide(currentSlide);
  }
  
  setInterval(nextSlide, 3500);
  showSlide(currentSlide);
  
  
  //------------- Funcion de filtros de platillos -------------------------------
  const minPrecioInput = document.getElementById("minPrecio");
  const maxPrecioInput = document.getElementById("maxPrecio");
  
  minPrecioInput.addEventListener("input", function () {
    if (parseInt(minPrecioInput.value) < 17) {
      minPrecioInput.value = 17;
    }
    if (parseInt(minPrecioInput.value) > parseInt(maxPrecioInput.value)) {
      minPrecioInput.value = maxPrecioInput.value;
    }
  });
  
  maxPrecioInput.addEventListener("input", function () {
    if (parseInt(maxPrecioInput.value) > 125) {
      maxPrecioInput.value = 125;
    }
    if (parseInt(maxPrecioInput.value) < parseInt(minPrecioInput.value)) {
      maxPrecioInput.value = minPrecioInput.value;
    }
  });
  
  // Agregar funcionalidad de selección para los descuentos
  const descuentos = document.querySelectorAll(".descuento");
  
  descuentos.forEach((button) => {
    button.addEventListener("click", function () {
      descuentos.forEach((btn) => btn.classList.remove("active"));
      button.classList.add("active");
    });
  });
  
  
  const ratingInput = document.getElementById("rating");
  const starsSpan = document.getElementById("stars");
  
  ratingInput.addEventListener("input", function () {
    let stars = "";
    const ratingValue = parseInt(ratingInput.value);
  
    // Validar que el valor esté dentro del rango 1-5
    if (ratingValue < 1) {
      ratingInput.value = 1;
    } else if (ratingValue > 5) {
      ratingInput.value = 5;
    }
  
    // Generar estrellas visualmente
    for (let i = 1; i <= 5; i++) {
      stars += i <= ratingValue ? "★" : "☆";
    }
    starsSpan.textContent = stars;
  });
  
  
  //LOGIN DINAMICO ----------------------------------------------
  const container = document.querySelector(".container");
  const btnSignIn = document.getElementById("btn-sign-in");
  const btnSignUp = document.getElementById("btn-sign-up");
  
  btnSignIn.addEventListener("click",()=>{
     container.classList.remove("toggle");
  });
  btnSignUp.addEventListener("click",()=>{
     container.classList.add("toggle");
  });
  
  
  
  