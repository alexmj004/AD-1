import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// PASOS A SEGUIR:
// -> CREAMOS UNA LISTA DONDE ALMACENAREMOS OBJETOS TIPO COCHE.
// -> AÑADIMOS UNA OPCIÓN PARA TENER ESCRITO EN EL FICHERO OBJETOS TIPO COCHE.
// -> COMPROBAMOS SÍ EL FICHERO ESTÁ O NO VACÍO. SÍ NO LO ESTÁ SE AÑADEN LOS OBJETOS QUE CONTENGA A LA LISTA.
// -> CREAMOS EL MENÚ CON LAS LLAMADAS A LOS MÉTODOS CORRESPONDIENTES.
// -> ESPECIFICAMOS LA FUNCIONALIDAD DE CADA UNO DE LOS MÉTODOS DEL MENÚ.
// -> AL SALIR DEL MENÚ, LOS COCHES DE LA LISTA SON ESCRITOS EN EL FICHERO.

public class Concesionario {
    static ArrayList<Coche> listCoches = new ArrayList<>();;
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        objetosAlIncio(new Coche(1,"5678YYY","CHEVROLET","CRUCE","GRIS"));



        File file = new File("src/main/java/coches.dat");
        if(!file.exists()){
            System.out.println("Lista vacía");

        }else{
            ObjectInputStream objectInputStream = null;
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(file));
                Coche coche = (Coche) objectInputStream.readObject();
                listCoches.add(coche);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }



        int opcion;
        do{

            System.out.println("1. Añadir nuevo coche");
            System.out.println("2. Borrar coche por id");
            System.out.println("3. Consulta coche por id");
            System.out.println("4. Listado de coches");
            System.out.println("5. Exportar coches a fichero txt");
            System.out.println("6. Terminar el programa");

            System.out.println("Elige una de las opiciones:");
            opcion = scanner.nextInt();

            // -> Opciones a elegir:
            switch (opcion){
                case 1:

                    // Solictamos al usuario que inserte los datos del coche a insertar.
                    System.out.println("ID:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("MATRÍCULA:");
                    String matriculate = scanner.nextLine();
                    System.out.println("MARCA:");
                    String marca = scanner.nextLine();
                    System.out.println("MODELO:");
                    String model = scanner.nextLine();
                    System.out.println("COLOR:");
                    String color = scanner.nextLine();

                    // No se permite duplicar el id ni la matricula.
                    for(Coche car : listCoches){
                        if(!(car.getId()==id)){
                            if(!(car.getMatricula().equals(matriculate))){
                                insertar(new Coche(id,matriculate,marca,model,color));
                            }else{
                                System.out.println("NO PUEDE HABER DOS COCHES CON MISMA MATRÍCULA.");
                            }

                        }else{
                            System.out.println("NO PUEDE HABER DOS COCHES CON MISMO ID.");
                        }
                    }

                    break;
                case 2:
                    // Solicitamos al usuario el id del coche a eliminar de la lista.
                    System.out.println("ID DEL COCHE QUE QUIERAS ELIMINAR:");
                    int identificador = scanner.nextInt();

                    borrar(identificador);
                    break;
                case 3:
                    // Solicitamos al usuario el id del coche a consultar:
                    System.out.println("ID DEL COCHE QUE QUIERAS CONSULTAR:");
                    int id2 = scanner.nextInt();
                    consultar(id2);
                    break;
                case 4:
                    listar();
                    break;
                case 5:
                    exportar();
                    break;


            }
        }while(opcion!=6);


        escrituraCoches("src/main/java/coches.dat");
        System.out.println("CONSULTA EL ARCHIVO coches.dat");

    }



    // Métodos del menú:
    public static void insertar(Coche coche){

        listCoches.add(coche);
        listar();
    }
    public static void borrar(int id){
        Coche cocheEliminar = null;
        for(Coche car:listCoches){
            if(car.getId() == id){
                cocheEliminar = car;
            }
        }
        listCoches.remove(cocheEliminar);



    }
    public static void consultar(int id){
        for(Coche coche:listCoches){
            if (id==coche.getId()){
                System.out.println(coche);
            }
        }
    }
    public static void listar(){
        if(!listCoches.isEmpty()){
            for(Coche car:listCoches){
                System.out.println(car);
            }
        }
    }
    public static void exportar(){
        File file = new File("src/main/java/coches.csv");
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(listCoches.toString().split(";"));

            System.out.println("EXPORTADO CON ÉXITO.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }



    // Método escrituraCoches() -> escribe en la ruta indicada los objetos guardados en el ArrayList:
    public static void escrituraCoches(String path){
        File file = new File(path);
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(listCoches.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    // Método objetosAlIncio() ->  para añadir al fichero objetos.dat, objetos tipo Coche antes de compilar el programa.
    public static void objetosAlIncio(Coche coche){
        ObjectOutputStream objectOutputStream = null;
        try {
            File file = new File("src/main/java/coches.dat");
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(coche);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}

