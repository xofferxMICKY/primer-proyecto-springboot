const base_url_productos = "http://localhost:8080/api/productos";
const base_url_categorias = "http://localhost:8080/api/categorias";

let carrito = [];

// Inicializar DataTable
const tablaProductos = new DataTable('#tablaProductos', {
    ajax: {
        url: base_url_productos,
        dataSrc: function (json) {
            return json.filter(producto => producto.stock > 0);
        }
    },
    columns: [
        { data: "idProducto" },
        { data: "nombre" },
        { data: "descripcion" },
        {
            data: "precio",
            render: data => "S/ " + parseFloat(data).toFixed(2)
        },
        { data: "categoria.nombre" },
        {
            data: "idProducto",
            render: data => `
                <button class="btn btn-success btn-sm" onclick="comprar(${data})">
                    Agregar al carrito
                </button>
            `
        }
    ]
});

// Cargar categorías para filtro
function cargarCategorias() {
    $.ajax({
        url: base_url_categorias,
        method: "GET",
        success: function (categorias) {
            const filtro = $('#categoriaFiltro');
            filtro.empty();
            filtro.append(`<option value="">Todas las categorías</option>`);
            categorias.forEach(cat => {
                filtro.append(`<option value="${cat.nombre}">${cat.nombre}</option>`);
            });
        }
    });
}

// Filtrar por categoría
$('#categoriaFiltro').on('change', function () {
    const categoriaSeleccionada = $(this).val();
    tablaProductos.column(4).search(categoriaSeleccionada).draw();
});

// Mostrar nombre del operario
$(document).ready(function () {
    const correo = $("#inputOperario").val();
    $.ajax({
        url: "http://localhost:8080/api/usuarios/usuario/" + correo,
        method: "GET",
        success: function (rest) {
            $("#nombreoperario").text(rest.nombre);
        }
    });

    cargarCategorias();
    actualizarCarritoUI();
});

// Agregar producto al carrito
function comprar(idProducto) {
    $.get(`http://localhost:8080/api/productos/${idProducto}`, function (producto) {
        const existente = carrito.find(p => p.idProducto === producto.idProducto);

        if (existente) {
            if (existente.cantidad < producto.stock) {
                existente.cantidad += 1;
            } else {
                Swal.fire("Stock insuficiente", "Ya agregaste el máximo disponible", "warning");
            }
        } else {
            carrito.push({
                idProducto: producto.idProducto,
                nombre: producto.nombre,
                precio: producto.precio,
                stock: producto.stock,
                cantidad: 1
            });
        }

        actualizarCarritoUI();
        Swal.fire("Agregado", "Producto agregado al carrito", "success");
    });
}

// Mostrar contador del carrito
function actualizarCarritoUI() {
    $("#carritoCount").text(carrito.reduce((sum, p) => sum + p.cantidad, 0));
}

// Mostrar el modal con los productos del carrito
function mostrarCarrito() {
    const tbody = $("#tablaCarritoBody");
    tbody.empty();
    let total = 0;

    carrito.forEach((item, index) => {
        const subtotal = item.precio * item.cantidad;
        total += subtotal;

        tbody.append(`
            <tr>
                <td>${item.nombre}</td>
                <td>S/ ${item.precio.toFixed(2)}</td>
                <td>
                    <input type="number" min="1" max="${item.stock}" value="${item.cantidad}"
                        onchange="cambiarCantidad(${index}, this.value)" class="form-control form-control-sm" />
                </td>
                <td>S/ ${subtotal.toFixed(2)}</td>
                <td>
                    <button class="btn btn-danger btn-sm" onclick="eliminarDelCarrito(${index})">
                        🗑
                    </button>
                </td>
            </tr>
        `);
    });

    $("#totalCarrito").text(`S/ ${total.toFixed(2)}`);
    $("#modalCarrito").modal("show");
}

// Cambiar cantidad en el carrito desde input
function cambiarCantidad(index, nuevaCantidad) {
    nuevaCantidad = parseInt(nuevaCantidad);
    const item = carrito[index];
    if (nuevaCantidad > 0 && nuevaCantidad <= item.stock) {
        item.cantidad = nuevaCantidad;
    } else {
        Swal.fire("Error", "Cantidad fuera de rango", "error");
    }
    mostrarCarrito();
    actualizarCarritoUI();
}

// Eliminar producto del carrito
function eliminarDelCarrito(index) {
    carrito.splice(index, 1);
    mostrarCarrito();
    actualizarCarritoUI();
}

// Confirmar compra (venta completa del carrito)
$("#formCarrito").submit(function (e) {
    e.preventDefault();

    const correo = $("#inputOperario").val();

    if (carrito.length === 0) {
        Swal.fire("Carrito vacío", "Agrega productos antes de comprar", "info");
        return;
    }

    $.get(`http://localhost:8080/api/usuarios/usuario/${correo}`, function (usuario) {
        const venta = {
            usuario: { idUsuario: usuario.idUsuario },
            total: carrito.reduce((sum, p) => sum + p.precio * p.cantidad, 0)
        };

        $.ajax({
            url: "http://localhost:8080/api/ventas",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(venta),
            success: function (ventaGuardada) {
                const peticiones = carrito.map(item => {
                    return $.ajax({
                        url: "http://localhost:8080/api/venta-productos",
                        method: "POST",
                        contentType: "application/json",
                        data: JSON.stringify({
                            venta: { idVenta: ventaGuardada.idVenta },
                            producto: { idProducto: item.idProducto },
                            cantidad: item.cantidad,
                            precioUnitario: item.precio
                        })
                    });
                });

                Promise.all(peticiones).then(() => {
                    Swal.fire("¡Compra realizada!", "Gracias por tu compra", "success");
                    carrito = [];
                    actualizarCarritoUI();
                    $("#modalCarrito").modal("hide");
                    tablaProductos.ajax.reload();
                });
            }
        });
    });
});
