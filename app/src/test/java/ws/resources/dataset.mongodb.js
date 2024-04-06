db = connect( 'mongodb://root:example@localhost:27017/proyecto?authSource=admin' );

db.Clientes.insertMany([
    {
        _id: 'Cliente1',
        nombre: 'Juan',
        password: 'mipassword',
        email: 'juan@email.com',
        fotoPerfil: 'mi foto',
        nickname: 'juanito',
        ciudad: 'Armenia',
        estado: 'ACTIVO',
        telefonos: ['3141231234', '3151231234'],
        bloqueos: [],
        favoritos: [],
        historial: [],
        _class: 'ws.modelo.documentos.Cliente'
    },
    {
        _id: 'Cliente2',
        nombre: 'Maria',
        password: 'mipassword',
        email: 'maria@email.com',
        fotoPerfil: 'mi foto',
        nickname: 'maria',
        ciudad: 'Armenia',
        estado: 'ACTIVO',
        telefonos: ['3141231234'],
        bloqueos: [{
                    _id:'bloqueo1', 
                    fechaInicio: new Date(), 
                    fechaFinal: new Date(), 
                    codigoModerador: 'moderador1',
                    motivo:'Aqui paso algo'
                }],
        favoritos: [],
        historial: [],
        _class: 'ws.modelo.documentos.Cliente'
    },
    {
        _id: 'Cliente3',
        nombre: 'Pedro',
        password: 'mipassword',
        email: 'pedro@email.com',   
        fotoPerfil: 'mi foto',
        nickname: 'pedrito',
        ciudad: 'Armenia',
        estado: 'ACTIVO',
        telefonos: ['3141231234'],
        bloqueos: [],
        favoritos: [],
        historial: [],
        _class: 'ws.model.documentos.Cliente'
    }
]);

db.Negocios.insertMany([
    {
        _id: 'Negocio1',
        nombre: 'Restaurante Mexicano',
        descripcion: 'Restaurante de comida mexicana en Armenia',
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
            },
        codigoCliente: 'Cliente1',
        tipoNegocio: 'RESTAURANTE',
        estadoRegistro: 'ACTIVO',
        telefonos: ['1234567', '7654321'],
        horarios: [
            {
            dia: 'LUNES',
            horaInicio: '08:00',
            horaFin: '20:00'
            }
        ],
        imagenes: ['imagen1', 'imagen2'],
        historialRevisiones: [],
        _class: 'ws.model.documentos.Negocio'
    }
]);

db.Comentarios.insertMany([
    {
        _id: 'comentario1',
        fecha: new Date(),
        calificacion: 5,
        mensaje: "Excelente sitio, muy buena atención",
        codigoCliente: 'Cliente1',
        codigoNegocio: 'Negocio1',
        mensaje: 'Hola esta genial',
        respuesta: 'gracias',
        estadoComentario: 'ACTIVO',
        _class: 'ws.model.documentos.Comentario'
    }
]);

db.Reportes.insertMany([
    {
        _id: 'reporte1',
        horaInicio: new Date(),
        mensaje: 'Irresponsable',
        codigoComentario: 'comentario1',
        estadoReporte: 'PROCESO',
        _class: 'ws.model.documentos.Reportes'
    }
])

db.Moderadores.insertMany([
    {
        _id: 'moderador1',
        nombre: 'pachito',
        password: 'password',
        email: 'hola@gmail.com',
        estadoRegistro: 'ACTIVO',
        _class: 'ws.model.documentos.Moderadores'
    }
])