// Función para cargar los platillos del localStorage
function cargarPlatillos() {
    const platillosSeleccionados = JSON.parse(localStorage.getItem("platillosSeleccionados")) || [];
    const listaPlatillosPago = document.getElementById("listaPlatillosPago");
    const subtotalElement = document.getElementById("subtotal");
    const comisionElement = document.getElementById("comision");
    const totalElement = document.getElementById("total");

    listaPlatillosPago.innerHTML = "";
    let platillosString = "";
    let subtotal = 0;

    if (platillosSeleccionados.length === 0) {
        listaPlatillosPago.innerHTML = "<li>No hay platillos seleccionados.</li>";
        subtotalElement.textContent = "s/ 0.00";
        comisionElement.textContent = "s/ 0.00";
        totalElement.textContent = "s/ 0.00";
        return;
    }

    platillosSeleccionados.forEach((platillo) => {
        const listItem = document.createElement("li");
        const precio = parseFloat(platillo.precio);
        const cantidad = parseInt(platillo.cantidad);
        const totalPlatillo = precio * cantidad;

        subtotal += totalPlatillo;
        listItem.textContent = `${platillo.nombre} - S/ ${precio.toFixed(2)} x ${cantidad}`;
        listaPlatillosPago.appendChild(listItem);
    });

    subtotalElement.textContent = `s/ ${subtotal.toFixed(2)}`;
    const comision = subtotal * 0.1;
    comisionElement.textContent = `s/ ${comision.toFixed(2)}`;
    const total = subtotal + comision;
    totalElement.innerHTML = `<strong>s/ ${total.toFixed(2)}</strong>`;
}

function pagar() {
    const compraData = {
        subtotal: parseFloat(document.getElementById("subtotal").textContent.replace("s/ ", "")),
        total: parseFloat(document.getElementById("total").textContent.replace("s/ ", ""))
    };

    fetch("/compras/insertar", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(compraData)
    })
    .then(response => response.text())
    .then(data => {
        alert(data); // Muestra la respuesta del servidor
        if (data.includes("exitosamente")) {
            localStorage.removeItem("platillosSeleccionados"); //Elimina platillos seleccionados
            sessionStorage.removeItem("mesasSeleccionadas"); //Elimina mesas seleccionadas
            window.location.href = "inicio"; //Redirecciona a inicio
        }
    })
    .catch(error => {
        alert("Error al procesar la compra.");
        console.error("Error:", error);
    });
}
//Cargar mesas en la lista y guardarlas
function cargarMesasReservadas() {
    const mesasReservadas = JSON.parse(sessionStorage.getItem("mesasSeleccionadas")) || [];
    const listaMesasReservadas = document.getElementById("mesasReservadasList");
    listaMesasReservadas.innerHTML = "";

    let mesasString = "";

    if (mesasReservadas.length === 0) {
        listaMesasReservadas.innerHTML = "<li>No hay mesas reservadas.</li>";
        return;
    }

    mesasReservadas.forEach((mesa) => {
        mesasString += `${mesa}, `;
        const listItem = document.createElement("li");
        listItem.textContent = `${mesa}`;
        listaMesasReservadas.appendChild(listItem);
    });
    sessionStorage.setItem("mesasString", mesasString.slice(0, -2));
}


window.onload = function () {
    cargarPlatillos();
    cargarMesasReservadas();
};