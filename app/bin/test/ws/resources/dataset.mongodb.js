db = connect( 'mongodb://root:example@localhost:27017/proyecto?authSource=admin' );

db.Clientes.insertMany([
    {
        _id: 'Cliente1',
        nombre: 'Juan',
        password: '$2a$10$tzAZK2cl78/90fMcJSVt7O25jz1k.QAqOW9uHQUPQmk.PsvU7L76O',
        email: 'juan@email.com',
        fotoPerfil: 'mi foto',
        nickname: 'juanito',
        ciudad: 'Armenia',
        estadoRegistro: 'ACTIVO',
        codigoRecuperacion: '0000',
        telefonos: ['3141231234', '3151231234'],
        bloqueos: [],
        favoritos: [],
        historial: [],
        _class: 'ws.modelo.documentos.Cliente'
    },
    {
        _id: 'Cliente2',
        nombre: 'Maria',
        password: '$2a$10$tzAZK2cl78/90fMcJSVt7O25jz1k.QAqOW9uHQUPQmk.PsvU7L76O',
        email: 'maria@email.com',
        fotoPerfil: 'mi foto',
        nickname: 'maria',
        ciudad: 'Armenia',
        estadoRegistro: 'ACTIVO',
        codigoRecuperacion: '0000',
        telefonos: ['3141231234'],
        bloqueos: [{
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
        password: '$2a$10$tzAZK2cl78/90fMcJSVt7O25jz1k.QAqOW9uHQUPQmk.PsvU7L76O',
        email: 'pedro@email.com',   
        fotoPerfil: 'mi foto',
        nickname: 'pedrito',
        ciudad: 'Armenia',
        estadoRegistro: 'ACTIVO',
        codigoRecuperacion: '0000',
        telefonos: ['3141231234'],
        bloqueos: [],
        favoritos: [],
        historial: [],
        _class: 'ws.model.documentos.Cliente'
    }
]);

db.Negocios.insertMany([
    {
        _id:'Negocio1',
        nombre: 'Las salchipapas',
        descripcion: 'Ricas',
        Ubicacion: {
            longitud: 20,
            latitud: 40
        },
        codigoCliente: 'Cliente1',
        tipoNegocio: 'CAFETERIA',
        estadoNegocio: 'PENDIENTE',
        telefonos: [
            '313141254'
        ],
        historialRevisiones: [],
        horarios: [
            {
                horaInicio: 10,
                horaFin: 17,
                dia: 'SUNDAY'
            }
        ],
        imagenes: [
            'Foto'
        ],

        popularidad: 5,
        _class: 'ws.model.documentos.Negocio'
    },
    {
        _id:'Negocio2',
        nombre: 'Las tostiempanadas',
        descripcion: 'Ricas',
        Ubicacion: {
            longitud: 20.54,
            latitud: 40.12
        },
        codigoCliente: 'Cliente2',
        tipoNegocio: 'CAFETERIA',
        estadoNegocio: 'APROBADO',
        telefonos: [
            '313141254',
            '312351251'
        ],
        historialRevisiones: [],
        horarios: [
            {
                horaInicio: 14,
                horaFin: 19,
                dia: 'MONDAY'
            }
        ],
        imagenes: [
            'Foto',
            'otra foto'
        ],
        popularidad: 3,
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
    },

    {
        _id: 'Moderador1',
        nombre: 'Jere',
        password: '$2a$10$FCB5OTZS/f7Dm5hHxTs/XuYmDHJJsCuC49p2vFCEl.onG69ozIE3G',
        email: 'moderador@gmail.com',
        estadoRegistro: 'ACTIVO',
        _class: 'ws.model.documentos.Moderador'
    }
])