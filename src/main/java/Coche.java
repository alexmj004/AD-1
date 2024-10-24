import lombok.*;

import java.io.Serializable;
import java.rmi.server.UID;
import java.util.Objects;

// PASOS A SEGUIR:
// -> DEFINIMOS LAS PROPIEDADES DE LA CLASE COCHE.
// -> A TRAVÉS DE LA LIBRERÍA LOMBOK AÑADIMOS LOS CONSTRUCTORES Y MÉTODOS CORRESPONDIENTES.

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Coche implements Serializable {

    private static final Long serialVersionUID = 12345L;


    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;


}
