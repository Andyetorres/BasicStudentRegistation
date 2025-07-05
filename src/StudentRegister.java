import java.util.Scanner;

public class StudentRegister {
    
    static String nameStudent = "";
    static double note1,note2,note3;
    static double average;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean leave = false;
        
        do{
        viewMenu();

        var opcion = leerEntero(scanner, "Ingrese su opcion: \n");
        switch (opcion) {
                case 1:
                    if (nameStudent == ""){
                        registerStudentInfo(scanner);
                    }else{
                        System.out.println("¿Desea reescribir todo?");
                        var validation = leerEntero(scanner,"Ingrese '1' si u 'otro numero' no\n");
                        if (validation == 1){
                            registerStudentInfo(scanner);
                        }else{
                            break;
                        }
                    }
                    break;
                case 2:
                    viewStudentInfo();
                    break;
                case 3:
                    if (nameStudent == ""){
                        System.out.println("\nAun no se ha registrado ninguna nota");                        
                    }else{
                        
                        average = calculateStudentAverage();
                        System.out.println("\nEl promedio del estudiante es: " + String.format("%.2f", average));
                    }

                    break;
                case 4:
                    cleanAllData();
                    break;
                case 5:
                    if(nameStudent!=""){
                        String sure = "";
                        do {
                            System.out.print("¿Está seguro? (si/no): ");
                            sure = scanner.nextLine().trim().toLowerCase();
                        } while (!sure.equals("no")&&!sure.equals("si"));

                        if (sure.equals("si")) {
                            passMateria();
                        }
                        break;
                    }else{
                        System.out.println("Primero registre un estudiante");
                        break;
                    }
                case 0:
                    System.out.println("Saliendo de la aplicacion.");
                    leave = true;
                    break;
                default:
                    System.out.println("Opcion inválida");
                    break;
            }
        }while(!leave);

        scanner.close();
    }

    private static int leerEntero(Scanner teclado, String mensaje) {
        int entrada = 0;
        boolean validate = false;

        do {
            System.out.print(mensaje);

            if (teclado.hasNextInt()) {
                entrada = teclado.nextInt();
                validate = true;
            } else {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
                validate = false;
                teclado.nextLine();
            }

        } while (!validate);

        teclado.nextLine();
        return entrada;
    }

    private static void viewMenu(){

            String cadena = "";
            cadena = "\n--- Sistema de Registro de Estudiantes ---\n"+
                "-------------------------------------------------------\n"+
                        "1. Registrar datos de un estudiante\n"+
                        "2. Mostrar datos del estudiante actual\n"+
                        "3. Calcular promedio de notas del estudiante actual\n"+
                        "4. Limpiar datos\n"+
                        "5. Validar si paso la materia\n"+
                        "0. Salir\n"+
                        "-------------------------------------------------------\n"
                        ;
            System.out.println(cadena);
    }

    private static void registerStudentInfo(Scanner scanner){
        int confi;

        do{
            boolean validate = false;

            do{
            System.out.print("Ingrese el nombre del estudiante: ");
            nameStudent = scanner.nextLine();
            validate = validateName(nameStudent,validate);
            }while(!validate);
            validate = false;

            do {
                System.out.print("Ingrese la primera nota: ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Debe ingresar un número valido (no deje el campo vacío).");
                    validate = false;
                } else {
                    try {
                        note1 = Double.parseDouble(input);
                        validate = validateNote(note1, validate);
                    } catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un número valido (use punto para decimales).");
                        validate = false;
                    }
                }
            } while (!validate);

            validate = false;

            do {
                System.out.print("Ingrese la segunda nota: ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Debe ingresar un número valido (no deje el campo vacío).");
                    validate = false;
                } else {
                    try {
                        note2 = Double.parseDouble(input);
                        validate = validateNote(note2, validate);
                    } catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un número valido (use punto para decimales).");
                        validate = false;
                    }
                }
            } while (!validate);

            validate = false;

            do {
                System.out.print("Ingrese la tercera nota: ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Debe ingresar un número valido (no deje el campo vacío).");
                    validate = false;
                } else {
                    try {
                        note3 = Double.parseDouble(input);
                        validate = validateNote(note3, validate);
                    } catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un número valido (use punto para decimales).");
                        validate = false;
                    }
                }
            } while (!validate);

            validate = false;
        System.out.println("¿Desea registrar estos datos?");
            confi = leerEntero(scanner, "Ingrese '1' para registrar u otro numero para reescribir: ");
        } while (confi != 1);
        System.out.println("Datos registrados");
    }

    private static void viewStudentInfo(){
        String cadena = "";

        if(nameStudent != ""){
            cadena ="\nEstudiante Registrado:\n"+
                    nameStudent+"\n"+
                        "Notas:\n"+
                    "Primera nota: "+String.format("%.2f", note1)+
                    "\nSegunda nota: "+String.format("%.2f", note2)+
                    "\nTercera nota: "+String.format("%.2f", note3);

            System.out.println(cadena);
        }else{
            System.out.println("\nAun no se ha registrado ningun estudiante");
        };
    }

    private static double calculateStudentAverage(){
        double average = 0.0;
        average = (note1 + note2 + note3) / 3;
        return average;
    }

    private static boolean validateName (String nameStudent,boolean validate){
        nameStudent = nameStudent.trim();
        validate=nameStudent.length() >= 2 && nameStudent.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");
        if (validate == true){
            return true;
        }else{
            System.out.println("El nombre que ingreso no es valido");
            return false;
        }
    }

    private static boolean validateNote (double note,boolean validate ){

        if (note <= 5 && note >= 0){
            return true;
        }else{
            System.out.println("El número esta fuera de rango");
            return false;
        }
    }

    private static void cleanAllData(){
        nameStudent = "";
        note1=0.0;
        note2=0.0;
        note3=0.0;
        System.out.println("Datos lavados :D");
    }

    private static void passMateria(){
        average = calculateStudentAverage();
        System.out.println(average <3 ? "El estudiante no paso la materia" :
                "El estudiante paso la materia");
    }
}