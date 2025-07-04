const base_url = "http://localhost:8080/api/usuarios";

const modalCrear = $('#modalCrear');
const modalEditar = $('#modalEditar');

// Cargar tabla
const tabla = new DataTable('#tablaUsuarios', {
    ajax: {
        url: base_url,
        dataSrc: ""
    },
    columns: [
        { data: null, render: (data, type, row, meta) => meta.row + 1 },
        { data: "nombre" },
        { data: "correo" },
        { data: "rol.nombre" },
        {
            data: null,
            render: (data, type, row) => `
                <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-warning" onclick="editar(${row.idUsuario})">Editar</button>
                    <button type="button" class="btn btn-sm btn-danger" onclick="eliminar(${row.idUsuario})">Eliminar</button>
                </div>`
        }
    ]
});

// Cargar roles en select
function cargarRoles(selectId) {
    $.get("http://localhost:8080/api/roles", function (data) {
        let select = $(`#${selectId}`);
        select.empty();
        data.forEach(rol => {
            select.append(`<option value="${rol.idRol}">${rol.nombre}</option>`);
        });
    });
}

// CREAR
function crear() {
    $('#nombre').val('');
    $('#correo').val('');
    $('#clave').val('');
    cargarRoles('rol');
    modalCrear.modal('show');
}

$('#formCrear').submit(function (e) {
    e.preventDefault();
    let data = new FormData(e.target);

    let sendData = {
        nombre: data.get('nombre'),
        correo: data.get('correo'),
        clave: data.get('clave'),
        rol: {
            idRol: data.get('idRol')
        }
    };

    $.ajax({
        type: "POST",
        url: base_url,
        data: JSON.stringify(sendData),
        contentType: "application/json; charset=utf-8",
        success: function (res) {
            modalCrear.modal('hide');
            Swal.fire(res.texto, '', res.tipo);
            tabla.ajax.reload();
        }
    });
});

// EDITAR
function editar(id) {
    $.get(`${base_url}/${id}`, function (res) {
        $('#idEditar').val(res.idUsuario);
        $('#nombreEditar').val(res.nombre);
        $('#correoEditar').val(res.correo);
        cargarRoles('rolEditar');
        setTimeout(() => { $('#rolEditar').val(res.rol.idRol); }, 200);
        modalEditar.modal('show');
    });
}

$('#formEditar').submit(function (e) {
    e.preventDefault();
    let data = new FormData(e.target);

    let sendData = {
        idUsuario: data.get('id'),
        nombre: data.get('nombre'),
        correo: data.get('correo'),
        rol: {
            idRol: data.get('idRol')
        }
    };

    $.ajax({
        type: "PUT",
        url: base_url,
        data: JSON.stringify(sendData),
        contentType: "application/json; charset=utf-8",
        success: function (res) {
            modalEditar.modal('hide');
            Swal.fire(res.texto, '', res.tipo);
            tabla.ajax.reload();
        }
    });
});

// ELIMINAR
function eliminar(id) {
    Swal.fire({
        title: '¿Estás seguro?',
        text: "No podrás revertirlo",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, eliminarlo'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: "DELETE",
                url: `${base_url}/${id}`,
                success: function (res) {
                    Swal.fire(res.texto, '', res.tipo);
                    tabla.ajax.reload();
                }
            });
        }
    });
}

document.getElementById("menu-toggle").addEventListener("click", function () {
    document.getElementById("wrapper").classList.toggle("toggled");
});