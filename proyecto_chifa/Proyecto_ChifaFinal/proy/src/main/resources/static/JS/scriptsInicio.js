function mostrarAlerta() {
    Swal.fire({
      icon: 'warning',
      title: 'Atención',
      text: 'Debes iniciar sesión para realizar una reserva.',
      confirmButtonText: 'OK'
    });
  }