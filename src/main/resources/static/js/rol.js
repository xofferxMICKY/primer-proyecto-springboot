const base_url = "http://localhost:8080/api/roles";
const modalCrear = $('#modalCrear');
const modalEditar = $('#modalEditar');

/** CARGAR LOS DATOS */
const tabla = new DataTable('#tablaRoles', {
    ajax: {
        url: base_url,
        dataSrc: ""
    },
    columns: [
        {
            "data": "0",
            "render": function (data, type, row, meta) {
                return meta.row + 1;
            }
        },
        {
            "data": "nombre"
        },
        {
            "data": "acciones",
            "render": function (data, type, row) {
                return `
                    <div class="btn-group">
                        <button type="button" class="btn btn-sm btn-warning" onclick="editar(${row.idRol})"><i class="fa fa-edit"> editar </i></button>
                        <button type="button" class="btn btn-sm btn-danger" onclick="eliminar(${row.idRol})"><i class="fa fa-trash"> eliminar </i></button>
                    </div>
                `;
            }
        }
    ]
});

/** METODO PARA AGREGAR */
function crear() {
    $('#nombre').val("");
    modalCrear.modal("show");
}

$('#formCrear').submit(function (e) {
    e.preventDefault();

    let data = new FormData(e.target);
    let sendData = {
        nombre: data.get('nombre')
    };

    $.ajax({
        type: "POST",
        url: base_url,
        data: JSON.stringify(sendData),
        contentType: "application/json; charset=utf-8",
        success: function (res) {
            Swal.fire({
                icon: res.tipo,
                title: res.tipo === "success" ? "Éxito" : (res.tipo === "warning" ? "Advertencia..." : "Oops..."),
                text: res.texto,
            });

            if (res.tipo === "success") {
                modalCrear.modal('hide');
                tabla.ajax.reload();
            }
        }
    });
});

/** FORMULARIO DE EDITAR */
function editar(id) {
    $.ajax({
        type: "GET",
        url: base_url + "/" + id,
        success: function (res) {
            $('#idEditar').val(res.idRol);
            $('#nombreEditar').val(res.nombre);
            modalEditar.modal("show");
        }
    });
}

$('#formEditar').submit(function (e) {
    e.preventDefault();

    let data = new FormData(e.target);
    let sendData = {
        idRol: data.get('id'),
        nombre: data.get('nombre')
    };

    $.ajax({
        type: "PUT",
        url: base_url,
        data: JSON.stringify(sendData),
        contentType: "application/json; charset=utf-8",
        success: function (res) {
            Swal.fire({
                icon: res.tipo,
                title: res.tipo === "success" ? "Éxito" : (res.tipo === "warning" ? "Advertencia..." : "Oops..."),
                text: res.texto,
            });

            if (res.tipo === "success") {
                modalEditar.modal("hide");
                tabla.ajax.reload();
            }
        }
    });
});

/** METODO PARA ELIMINAR */
function eliminar(id) {
    Swal.fire({
        title: '¿Estás seguro de eliminarlo?',
        text: 'No podrás revertirlo después!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        confirmButtonText: 'Sí, elíminalo'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: "DELETE",
                url: base_url + "/" + id,
                success: function (res) {
                    Swal.fire({
                        icon: res.tipo,
                        title: res.tipo === "success" ? "Éxito..." : (res.tipo === "warning" ? "Advertencia..." : "Oops..."),
                        text: res.texto,
                    });

                    if (res.tipo === "success") {
                        tabla.ajax.reload();
                    }
                }
            });
        }
    });
}

document.getElementById("menu-toggle").addEventListener("click", function () {
    document.getElementById("wrapper").classList.toggle("toggled");
});