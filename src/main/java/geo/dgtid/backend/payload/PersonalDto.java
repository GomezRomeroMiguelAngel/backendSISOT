package geo.dgtid.backend.payload;

import java.util.UUID;

import lombok.Data;

@Data
public class PersonalDto {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rfc;
    private String curp;
    private String email;

    public String generarUserName(){
        // Concatenar el nombre y apellido para formar un nombre de usuario
        String nombreUsuario = nombre.toLowerCase().substring(0, 3) 
        + apellidoPaterno.toLowerCase() + apellidoMaterno.toLowerCase();
        // Añadir una cadena aleatoria al final para evitar conflictos si hay usuarios con el mismo nombre
        nombreUsuario += UUID.randomUUID().toString().substring(0, 4);
        return nombreUsuario;
    }


    public static String generarPassword() {
        // Generar una contraseña aleatoria
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
