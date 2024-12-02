paypal.Buttons({
    createOrder: function(data, actions) {
        var totalText = document.getElementById('total').textContent.trim(); 
        console.log("Total texto: ", totalText);  
        
        var total = parseFloat(totalText.replace("s/", "").trim());
        console.log("Total numérico: ", total);  
        
        if (isNaN(total) || total <= 0) {
            alert("No tiene ningún producto en su carrito.");
            return; 
        }

        return actions.order.create({
            purchase_units: [{
                amount: {
                    value: total.toFixed(2) 
                }
            }]
        });
    },
    onApprove: function(data, actions) {
        return actions.order.capture().then(function(details) {
            Swal.fire({
            icon: 'success',
            title: 'Pago Completado',
            text: 'Gracias por tu compra',
            timer: 2000,
            showConfirmButton: false
        });
        // Redirigir después de 2 segundos
        setTimeout(function() {
            window.location.href = '/inicio'; 
        }, 2000);
        });
    },
    onCancel: function(data) {
        Swal.fire({
        icon: 'warning',
        title: 'Pago Cancelado',
        text: 'El pago fue cancelado',
        timer: 2000,
        showConfirmButton: false
    });
    }
}).render('#paypal-button-container');