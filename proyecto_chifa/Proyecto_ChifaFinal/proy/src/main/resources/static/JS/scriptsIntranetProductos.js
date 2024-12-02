
function cargarDatosPlatillo(id) {
    if (id) {
        fetch(`/intranet_productos/editar/${id}`)
            .then(response => response.json())
            .then(data => {
                // Llenar el formulario con los datos obtenidos
                document.getElementById('editId').value = data.id;
                document.getElementById('editNombre').value = data.nombre;
                document.getElementById('editDescripcion').value = data.descripcion;
                document.getElementById('editPrecio').value = data.precio;
                document.getElementById('editCalificacion').value = data.calificacion;
                document.getElementById('editImagen').value = data.imagen;
            })
            .catch(error => {
                console.error('Error al cargar los datos del platillo:', error);
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'No se pudieron cargar los datos del platillo. Inténtalo nuevamente.',
                    confirmButtonText: 'Aceptar'
                });
            });
    } else {
        console.error('ID del platillo no válido');
        Swal.fire({
            icon: 'warning',
            title: 'Advertencia',
            text: 'ID del platillo no válido.',
            confirmButtonText: 'Aceptar'
        });
    }
}



function guardarNuevoPlatillo() {
    const nombre = document.getElementById("nombre").value;
    const descripcion = document.getElementById("descripcion").value;
    const precio = parseFloat(document.getElementById("precio").value);
    const calificacion = parseFloat(document.getElementById("calificacion").value);
    const imagen = document.getElementById("imagen").value;

    const platillo = {
        nombre,
        descripcion,
        precio,
        calificacion,
        imagen
    };

    fetch('/intranet_productos/guardar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(platillo)
    })
    .then(response => response.json())
    .then(() => {
        Swal.fire({
            icon: 'success',
            title: 'Éxito',
            text: 'Platillo agregado exitosamente',
            timer: 2000,
            showConfirmButton: false
        }).then(() => {
            $('#modalNuevo').modal('hide'); // Cerrar modal
            window.location.reload(); // Actualizar tabla
        });
    })
    .catch(error => {
        console.error('Error al guardar el platillo:', error);
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Hubo un problema al guardar el platillo'
        });
    });
}

function guardarCambiosPlatillo() {
    const id = document.getElementById("editId").value;
    const nombre = document.getElementById("editNombre").value;
    const descripcion = document.getElementById("editDescripcion").value;
    const precio = parseFloat(document.getElementById("editPrecio").value);
    const calificacion = parseFloat(document.getElementById("editCalificacion").value);
    const imagen = document.getElementById("editImagen").value;

    const platillo = {
        id,
        nombre,
        descripcion,
        precio,
        calificacion,
        imagen
    };

    fetch('/intranet_productos/guardar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(platillo)
    })
    .then(response => response.json())
    .then(() => {
        Swal.fire({
            icon: 'success',
            title: 'Éxito',
            text: 'Platillo actualizado exitosamente',
            timer: 2000,
            showConfirmButton: false
        }).then(() => {
            $('#modalEditar').modal('hide'); // Cerrar modal
            window.location.reload(); // Actualizar tabla
        });
    })
    .catch(error => {
        console.error('Error al actualizar el platillo:', error);
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Hubo un problema al actualizar el platillo'
        });
    });
}

// Función para eliminar un platillo con confirmación y alerta de SweetAlert2
function eliminarPlatillo(button) {
    const id = button.getAttribute('data-id');  // Obtener el ID desde el atributo 'data-id'

    Swal.fire({
        title: '¿Estás seguro?',
        text: "Esta acción no se puede deshacer.",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            fetch(`/intranet_productos/eliminar/${id}`, {
                method: 'DELETE'
            })
            .then(response => response.text())
            .then(message => {
                Swal.fire({
                    icon: 'success',
                    title: 'Eliminado',
                    text: message,
                    confirmButtonText: 'Aceptar'
                }).then(() => {
                    location.reload(); // Refresca la página
                });
            })
            .catch(error => {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'No se pudo eliminar el platillo. Inténtalo nuevamente.',
                    confirmButtonText: 'Aceptar'
                });
                console.error('Error al eliminar el platillo:', error);
            });
        }
    });
}



// Función para guardar los cambios de un platillo editado
function guardarCambiosPlatillo() {
    const id = document.getElementById("editId").value;
    const nombre = document.getElementById("editNombre").value;
    const descripcion = document.getElementById("editDescripcion").value;
    const precio = parseFloat(document.getElementById("editPrecio").value);
    const calificacion = parseFloat(document.getElementById("editCalificacion").value);
    const imagen = document.getElementById("editImagen").value;

    // Crear objeto con los datos del platillo editado
    const platillo = {
        id: id,
        nombre: nombre,
        descripcion: descripcion,
        precio: precio,
        calificacion: calificacion,
        imagen: imagen
    };

    // Enviar los datos al servidor 
    fetch('/intranet_productos/guardar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(platillo)
    })
    .then(response => response.json())
    .then(data => {
        Swal.fire({
            icon: 'success',
            title: '¡Éxito!',
            text: 'Platillo actualizado exitosamente',
            confirmButtonText: 'Aceptar'
        }).then(() => {
            $('#modalEditar').modal('hide');  // Cerrar el modal
            window.location.reload();  // Actualizar la interfaz
        });
    })
    .catch(error => {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'No se pudo actualizar el platillo. Inténtalo nuevamente.',
            confirmButtonText: 'Aceptar'
        });
        console.error('Error al actualizar el platillo:', error);
    });
}
//cargar datos al actualizar
function cargarDatosPlatillo(id) {
    if (id) {
        fetch(`/intranet_productos/editar/${id}`)
            .then(response => response.json())
            .then(data => {
                // Llenar el formulario con los datos obtenidos
                document.getElementById('editId').value = data.id;
                document.getElementById('editNombre').value = data.nombre;
                document.getElementById('editDescripcion').value = data.descripcion;
                document.getElementById('editPrecio').value = data.precio;
                document.getElementById('editCalificacion').value = data.calificacion;
                document.getElementById('editImagen').value = data.imagen;
            })
            .catch(error => {
                console.error('Error al cargar los datos del platillo:', error);
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'No se pudieron cargar los datos del platillo. Inténtalo nuevamente.',
                    confirmButtonText: 'Aceptar'
                });
            });
    } else {
        console.error('ID del platillo no válido');
        Swal.fire({
            icon: 'warning',
            title: 'Advertencia',
            text: 'ID del platillo no válido.',
            confirmButtonText: 'Aceptar'
        });
    }
}


// Función para guardar un nuevo platillo
function guardarPlatillo() {
    const platillo = {
        nombre: document.getElementById('nombre').value,
        descripcion: document.getElementById('descripcion').value,
        precio: parseFloat(document.getElementById('precio').value),
        calificacion: parseFloat(document.getElementById('calificacion').value),
        imagen: document.getElementById('imagen').value
    };

    fetch('/intranet_productos/guardar', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(platillo)
    })
    .then(response => response.json())
    .then(() => {
        // Mostrar mensaje de éxito con SweetAlert2
        Swal.fire({
            icon: 'success',
            title: '¡Éxito!',
            text: 'Platillo guardado exitosamente.',
            confirmButtonText: 'Aceptar'
        }).then(() => {
            // Actualizar la interfaz (por ejemplo, recargar la página)
            window.location.reload();

            // Limpiar los campos del formulario
            document.getElementById('nombre').value = '';
            document.getElementById('descripcion').value = '';
            document.getElementById('precio').value = '';
            document.getElementById('calificacion').value = '';
            document.getElementById('imagen').value = '';

            // Cerrar el modal
            const modal = new bootstrap.Modal(document.getElementById('modalNuevo'));
            modal.hide();
        });
    })
    .catch(error => {
        console.error('Error al guardar el platillo:', error);
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'No se pudo guardar el platillo. Inténtalo nuevamente.',
            confirmButtonText: 'Aceptar'
        });
    });
}


// Función para rellenar el formulario de edición con los datos del platillo
function editarPlatillo(id) {
    fetch(`/intranet_productos/${id}`)
        .then(response => response.json())
        .then(platillo => {
            document.getElementById("editId").value = platillo.id;
            document.getElementById("editNombre").value = platillo.nombre;
            document.getElementById("editDescripcion").value = platillo.descripcion;
            document.getElementById("editPrecio").value = platillo.precio;
            document.getElementById("editCalificacion").value = platillo.calificacion;
            document.getElementById("editImagen").value = platillo.imagen;

            $('#modalEditar').modal('show'); // Mostrar modal
        })
        .catch(error => {
            console.error('Error al obtener los datos del platillo:', error);
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'No se pudieron obtener los datos del platillo'
            });
        });
}



// Función para eliminar un platillo con confirmación y alerta de SweetAlert2
function eliminarPlatillo(button) {
    const id = button.getAttribute('data-id');  // Obtener el ID desde el atributo 'data-id'

    Swal.fire({
        title: '¿Estás seguro?',
        text: "Esta acción no se puede deshacer.",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            fetch(`/intranet_productos/eliminar/${id}`, {
                method: 'DELETE'
            })
            .then(response => response.text())
            .then(message => {
                Swal.fire({
                    icon: 'success',
                    title: 'Eliminado',
                    text: message,
                    confirmButtonText: 'Aceptar'
                }).then(() => {
                    location.reload(); // Refresca la página
                });
            })
            .catch(error => {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'No se pudo eliminar el platillo. Inténtalo nuevamente.',
                    confirmButtonText: 'Aceptar'
                });
                console.error('Error al eliminar el platillo:', error);
            });
        }
    });
}
