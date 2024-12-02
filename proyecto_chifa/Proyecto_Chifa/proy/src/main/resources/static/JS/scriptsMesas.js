document.addEventListener("DOMContentLoaded", function() {
  const mesas = document.querySelectorAll(".mesa");
  const mesaSeleccionadaUl = document.getElementById("mesaSeleccionada");
  const deseleccionarTodoBtn = document.getElementById("deseleccionarTodo");
  const continuarBtn = document.getElementById("continuar");
  const fechaSeleccionadaSpan = document.getElementById("fechaSeleccionada");
  const seleccionarOtraFechaBtn = document.getElementById("seleccionarOtraFecha");

  let seleccionadas = []; // Almacenar mesas seleccionadas

  // Función para mostrar el selector de fecha y hora usando SweetAlert2
  function mostrarSelectorFecha() {
    Swal.fire({
      title: '¿Para qué día y hora quieres reservar?',
      html: `
        <input type="date" id="fecha" class="swal2-input" placeholder="Fecha">
        <input type="time" id="hora" class="swal2-input" placeholder="Hora">
      `,
      showCancelButton: true,
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancelar',
      preConfirm: () => {
        const fecha = Swal.getPopup().querySelector('#fecha').value;
        const hora = Swal.getPopup().querySelector('#hora').value;
        if (!fecha || !hora) {
          Swal.showValidationMessage('Por favor ingresa una fecha y hora válidas');
        }
        return { fecha: fecha, hora: hora };
      }
    }).then((result) => {
      if (result.isConfirmed) {
        fechaSeleccionadaSpan.textContent = `${result.value.fecha} a las ${result.value.hora}`;
        Swal.fire(`Reserva para el ${result.value.fecha} a las ${result.value.hora}`, '', 'success');
      } else {
        Swal.fire('Reserva cancelada', '', 'error');
      }
    });
  }

  // Mostrar selector de fecha/hora inicial
  mostrarSelectorFecha();

  // Función para seleccionar mesa
  mesas.forEach(mesa => {
    mesa.addEventListener("click", function() {
      const mesaNumero = mesa.textContent.trim();

      if (mesa.classList.contains("disponible")) {
        if (seleccionadas.length < 3) { // Limitar a 3 mesas seleccionadas
          mesa.classList.remove("disponible");
          mesa.classList.add("seleccionada");
          agregarSeleccion(mesaNumero);
          Swal.fire({
            title: 'Mesa Elegida',
            text: `Has elegido la mesa número ${mesaNumero}`,
            icon: 'success',
            confirmButtonText: 'Aceptar'
          });
        } else {
          Swal.fire({
            title: 'Límite alcanzado',
            text: 'Solo puedes seleccionar hasta 3 mesas.',
            icon: 'warning',
            confirmButtonText: 'Aceptar'
          });
        }
      } else if (mesa.classList.contains("seleccionada")) { // Permitir deselección
        mesa.classList.remove("seleccionada");
        mesa.classList.add("disponible");
        removerSeleccion(mesaNumero);
      }
    });
  });

  // Función para agregar mesa seleccionada a la lista
  function agregarSeleccion(mesaTexto) {
    seleccionadas.push(mesaTexto); // Añadir a las seleccionadas
    const li = document.createElement("li");
    li.textContent = mesaTexto;
    li.setAttribute("data-mesa", mesaTexto);
    mesaSeleccionadaUl.appendChild(li);
  }

  // Función para remover mesa seleccionada
  function removerSeleccion(mesaTexto) {
    seleccionadas = seleccionadas.filter(m => m !== mesaTexto); // Remover de seleccionadas
    const li = mesaSeleccionadaUl.querySelector(`li[data-mesa="${mesaTexto}"]`);
    if (li) {
      mesaSeleccionadaUl.removeChild(li);
    }
  }

  // Deseleccionar todas las mesas
  deseleccionarTodoBtn.addEventListener("click", function() {
    mesas.forEach(mesa => {
      if (mesa.classList.contains("seleccionada")) {
        mesa.classList.remove("seleccionada");
        mesa.classList.add("disponible");
      }
    });
    seleccionadas = []; // Limpiar la lista de seleccionadas
    mesaSeleccionadaUl.innerHTML = "";
  });

  // Validar y continuar
  continuarBtn.addEventListener("click", function(e) {
    if (seleccionadas.length === 0) {
      e.preventDefault(); // Evitar que continúe
      Swal.fire({
        title: 'Sin selección',
        text: 'Debes seleccionar al menos una mesa antes de continuar.',
        icon: 'warning',
        confirmButtonText: 'Aceptar'
      });
    } else {
      // Verificar si hay sesión de usuario activa
      const sesionActiva = true; // Cambia esto según tu lógica de sesión

      if (!sesionActiva) {
        e.preventDefault(); // Evitar que continúe
        Swal.fire({
          title: 'Inicio de sesión requerido',
          text: 'Debes iniciar sesión para continuar.',
          icon: 'warning',
          confirmButtonText: 'Aceptar'
        }).then(() => {
          window.location.href = 'login'; // Cambia esto por la ruta de tu login
        });
      } else {
        // Guardar la selección en la sesión (esto será manejado por el backend)
        sessionStorage.setItem('mesasSeleccionadas', JSON.stringify(seleccionadas));

        window.location.href = 'platillos'; // Cambia esto por la ruta correcta
      }
    }
  });

  // Al hacer clic en "Seleccionar otra fecha", vuelve a mostrar el selector de fecha
  seleccionarOtraFechaBtn.addEventListener("click", function() {
    mostrarSelectorFecha();
  });
});

