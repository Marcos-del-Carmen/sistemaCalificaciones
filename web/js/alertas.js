var matriculaAEliminar = null;

function confirmarEliminar(matricula) {
    var ventana = document.getElementById("ventanaConfirmacion");
    var matriculaSpan = document.getElementById("matriculaEliminar");
    matriculaSpan.textContent = matricula; // Asigna la matrícula al span
    ventana.style.display = "flex"; // Muestra la ventana centrada
    matriculaAEliminar = matricula;
}

function eliminarRegistro() {
    if (matriculaAEliminar) {
        window.location.href = "SvMenu?accion=eliminarM&matricula=" + matriculaAEliminar;
    }
    cerrarVentana(); // Cierra la ventana después de confirmar
}

function cerrarVentana() {
    var ventana = document.getElementById("ventanaConfirmacion");
    ventana.style.display = "none";
    matriculaAEliminar = null;
}

function mostrarFormAlumnos() {
    var formulario = document.querySelector('.cont-form-alumnos');
    var boton = document.querySelector('.toggle-button');

    if (formulario.style.display === 'none') {
        formulario.style.display = 'block';
        boton.textContent = 'Ocultar formulario';
    } else {
        formulario.style.display = 'none';
        boton.textContent = 'Mostrar formulario';
    }
}

function toggleFormVisibility() {
    var contEntradas = document.querySelector('.cont-entradas');
    var toggleButton = document.getElementById('toggleButton');

    if (contEntradas.style.display === 'none') {
        contEntradas.style.display = 'block';
        toggleButton.textContent = 'Ocultar formulario';
    } else {
        contEntradas.style.display = 'none';
        toggleButton.textContent = 'Mostrar formulario';
    }
}