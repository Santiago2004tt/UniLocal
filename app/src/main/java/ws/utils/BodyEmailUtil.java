package ws.utils;

public class BodyEmailUtil {

    

    public static String emailRecuperarContrasenia(String nombreUsuario, String codigo) {
        return String.format("""
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <style>
                        /* Estilos generales */
                        body {
                            font-family: Arial, sans-serif;
                            margin: 0;
                            padding: 0;
                            background-color: #f4f4f4;
                        }
                                
                        /* Estilos para el encabezado */
                        .header {
                            background-color: grey;
                            color: white;
                            padding: 20px;
                            text-align: center;
                        }
                                
                        /* Estilos para el contenido */
                        .content {
                            padding: 20px;
                            background-color: #ffffff;
                            text-align: center;
                        }
                                
                        /* Estilos para los enlaces */
                        a {
                            color: #0073e6;
                            text-decoration: none;
                        }
                                
                        /* Estilos para el pie de página */
                        .footer {
                            background-color: #f4f4f4;
                            padding: 20px;
                            text-align: center;
                        }
                    </style>
                </head>
                <body>
                    <table width="100%%" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="header">
                                <h1>Recuperación de contraseña</h1>
                            </td>
                        </tr>
                        <tr>
                            <td class="content">
                                <table width="100%%" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td style="text-align: center;">
                                            <p style="font-size: 18px; color: #333;">Estimado/a %s,</p>
                                            <p style="font-size: 16px; color: #555;">Hemos recibido una solicitud para restablecer la contraseña de su cuenta en panaderíaTT. Para garantizar la seguridad de su cuenta, hemos generado un código de recuperación que debe utilizar para completar este proceso.</p>
                                            <p style="font-size: 23px; color: black;">Su código de recuperación es: <span style="font-weight: bold;">%s<span></p>
                                            <p style="font-size: 16px; color: #555;">Atentamente,</p>
                                            <p style="font-size: 18px; color: #333;">Panaderia TT</p>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </body>
                </html>
                """,
                nombreUsuario,
                codigo);
    }

    

    
}
