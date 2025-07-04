const base_url = "http://localhost:8080/api/productos";

const modalCrear = $('#modalCrear');
const modalEditar = $('#modalEditar');

// Cargar categorías
function cargarCategorias(selectorId) {
    $.get("http://localhost:8080/api/categorias", function (data) {
        const select = $(selectorId);
        select.empty();
        data.forEach(cat => {
            select.append(`<option value="${cat.idCategoria}">${cat.nombre}</option>`);
        });
    });
}

// DataTable
const tabla = new DataTable('#tablaProductos', {
    ajax: {
        url: base_url,
        dataSrc: ""
    },
    columns: [
        { data: null, render: (data, type, row, meta) => meta.row + 1 },
        { data: "nombre" },
        { data: "descripcion" },
        { data: "precio" },
        { data: "stock" },
        { data: "categoria.nombre" },
        {
            data: "idProducto",
            render: function (data) {
                return `
                    <button class="btn btn-warning btn-sm" onclick="editar(${data})">Editar</button>
                    <button class="btn btn-danger btn-sm" onclick="eliminar(${data})">Eliminar</button>
                `;
            }
        }
    ]
});

// Crear
function crear() {
    $('#formCrear')[0].reset();
    cargarCategorias('#formCrear select[name="id_categoria"]');
    modalCrear.modal("show");
}

$('#formCrear').submit(function (e) {
    e.preventDefault();
    const data = Object.fromEntries(new FormData(e.target).entries());

    $.ajax({
        type: "POST",
        url: base_url,
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (res) {
            modalCrear.modal('hide');
            Swal.fire(res.texto, '', res.tipo);
            tabla.ajax.reload();
        }
    });
});

// Editar
function editar(id) {
    $.get(base_url + "/" + id, function (res) {
        $('#idEditar').val(res.idProducto);
        $('#nombreEditar').val(res.nombre);
        $('#descripcionEditar').val(res.descripcion);
        $('#precioEditar').val(res.precio);
        $('#stockEditar').val(res.stock);

        cargarCategorias('#categoriaEditar');
        setTimeout(() => {
            $('#categoriaEditar').val(res.categoria.idCategoria);
        }, 100);
        modalEditar.modal("show");
    });
}

$('#formEditar').submit(function (e) {
    e.preventDefault();
    const data = Object.fromEntries(new FormData(e.target).entries());

    $.ajax({
        type: "PUT",
        url: base_url,
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (res) {
            modalEditar.modal('hide');
            Swal.fire(res.texto, '', res.tipo);
            tabla.ajax.reload();
        }
    });
});

// Eliminar
function eliminar(id) {
    Swal.fire({
        title: "¿Estás seguro?",
        text: "Esto no se puede deshacer",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Sí, eliminar"
    }).then(result => {
        if (result.isConfirmed) {
            $.ajax({
                type: "DELETE",
                url: base_url + "/" + id,
                success: function (res) {
                    Swal.fire(res.texto, '', res.tipo);
                    tabla.ajax.reload();
                }
            });
        }
    });
}
// Sidebar toggle
document.getElementById("menu-toggle").addEventListener("click", function () {
    document.getElementById("wrapper").classList.toggle("toggled");
});