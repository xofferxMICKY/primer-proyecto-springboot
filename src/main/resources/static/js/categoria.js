const base_url = "http://localhost:8080/api/categorias";
const modalCrear = $('#modalCrear');
const modalEditar = $('#modalEditar');

const tabla = new DataTable('#tablaCategorias', {
    ajax: {
        url: base_url,
        dataSrc: ""
    },
    columns: [
        {
            data: null,
            render: (data, type, row, meta) => meta.row + 1
        },
        { data: "nombre" },
        {
            data: "acciones",
            render: (data, type, row) => `
                <button class="btn btn-sm btn-warning" onclick="editar(${row.idCategoria})">Editar</button>
                <button class="btn btn-sm btn-danger" onclick="eliminar(${row.idCategoria})">Eliminar</button>
            `
        }
    ]
});

function crear() {
    $('#nombre').val('');
    modalCrear.modal("show");
}

$('#formCrear').submit(function (e) {
    e.preventDefault();
    const data = { nombre: $('#nombre').val() };

    $.ajax({
        type: "POST",
        url: base_url,
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (res) {
            Swal.fire(res.texto, '', res.tipo);
            if (res.tipo === 'success') {
                modalCrear.modal('hide');
                tabla.ajax.reload();
            }
        }
    });
});

function editar(id) {
    $.get(base_url + "/" + id, function (res) {
        $('#idEditar').val(res.idCategoria);
        $('#nombreEditar').val(res.nombre);
        modalEditar.modal("show");
    });
}

$('#formEditar').submit(function (e) {
    e.preventDefault();
    const data = {
        idCategoria: $('#idEditar').val(),
        nombre: $('#nombreEditar').val()
    };

    $.ajax({
        type: "PUT",
        url: base_url,
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (res) {
            Swal.fire(res.texto, '', res.tipo);
            if (res.tipo === 'success') {
                modalEditar.modal("hide");
                tabla.ajax.reload();
            }
        }
    });
});

function eliminar(id) {
    Swal.fire({
        title: "¿Estás seguro?",
        text: "No podrás revertir esta acción",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Sí, eliminar",
    }).then(result => {
        if (result.isConfirmed) {
            $.ajax({
                type: "DELETE",
                url: base_url + "/" + id,
                success: function (res) {
                    Swal.fire(res.texto, '', res.tipo);
                    if (res.tipo === 'success') {
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