const listaPlatillosSeleccionados = document.getElementById('listaPlatillosSeleccionados');

//Función para guardar los platillos en localStorage
function guardarPlatillos() {
    const platillos = [];
    const existingItems = listaPlatillosSeleccionados.getElementsByTagName('li');
    for (let item of existingItems) {
        const nombre = item.querySelector('span').textContent.split(' - ')[0];
        const precio = item.querySelector('span').textContent.split(' - ')[1].replace('S/ ', '');
        const cantidad = item.querySelector('input').value;

        platillos.push({ nombre, precio, cantidad });
    }
    localStorage.setItem('platillosSeleccionados', JSON.stringify(platillos));
}

//Función para agregar platillo
function agregarPlatillo(nombre, precio, cantidad, imagen) {
    const platillosSeleccionados = JSON.parse(localStorage.getItem('platillosSeleccionados')) || [];

    const platilloExistente = platillosSeleccionados.find(item => item.nombre === nombre);

    if (platilloExistente) {
        platilloExistente.cantidad = parseInt(platilloExistente.cantidad) + parseInt(cantidad);
    } else {
        platillosSeleccionados.push({ nombre, precio, cantidad, imagen });
    }

    localStorage.setItem('platillosSeleccionados', JSON.stringify(platillosSeleccionados));
    guardarPlatillos(); //Llama a guardarPlatillos después de agregar
}


//Añadir eventos a los botones de seleccionar
document.querySelectorAll('.boton-seleccionar').forEach(button => {
    button.addEventListener('click', () => {
        const nombrePlatillo = button.getAttribute('data-nombre');
        const precioPlatillo = button.getAttribute('data-precio');

        let cantidadInput = document.createElement('input');
        cantidadInput.type = 'number';
        cantidadInput.min = '1';
        cantidadInput.value = '1';

        //Verificar si el platillo ya está en la lista
        let exists = false;
        const existingItems = listaPlatillosSeleccionados.getElementsByTagName('li');

        for (let item of existingItems) {
            if (item.textContent.includes(nombrePlatillo)) {
                exists = true;
                const cantidadElemento = item.querySelector('input');
                cantidadElemento.value = parseInt(cantidadElemento.value) + 1;
                agregarPlatillo(nombrePlatillo, precioPlatillo, '1');
                break;
            }
        }

        if (!exists) {
            const listItem = document.createElement('li');
            listItem.style.display = 'flex';
            listItem.style.justifyContent = 'space-between';
            listItem.style.alignItems = 'center';

            const nombreSpan = document.createElement('span');
            nombreSpan.textContent = `${nombrePlatillo} - S/ ${precioPlatillo}`;
            listItem.appendChild(nombreSpan);

            cantidadInput.style.width = '50px';
            cantidadInput.style.marginLeft = '10px';
            listItem.appendChild(cantidadInput);

            listaPlatillosSeleccionados.appendChild(listItem);
            agregarPlatillo(nombrePlatillo, precioPlatillo, cantidadInput.value);
        }
    });
});

document.getElementById('continuarPago').addEventListener('click', function() {
    const listaPlatillosSeleccionados = document.getElementById('listaPlatillosSeleccionados');
    
    //Verifica si hay platillos seleccionados
    if (listaPlatillosSeleccionados.children.length === 0) {
        // Muestra la alerta
        const confirmar = confirm("No has seleccionado ningún platillo. ¿Deseas continuar?");
        
        if (confirmar) {
            //Si el usuario acepta redirige a pago
            window.location.href = 'pago';
        }
    } else {
        // Si hay platillos seleccionados redirige pago
        window.location.href = 'pago';
    }
});
