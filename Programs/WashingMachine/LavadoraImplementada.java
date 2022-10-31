import java.util.Scanner;

/*
 *Clase creada para pedir datos a los usuarios de la lavadora 1
 *Se hace uso de la clase LavadoraSamsung creada por el desarrollador externo
 *Se crea un package llamado libreria, se copia la clase que fue enviada por el desarrollador externo
  y se importa en esta clase main.
 *Se piden datos por teclado y se realiza la implementacion del metodo visible cicloFinalizado().
*/

public class LavadoraImplementada {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Seleccione el tipo de ropa: ");
        System.out.println("Seleccione 1 - ropa blanca / 2 - ropa color");
        int tipoRopa = sc.nextInt();
        
        System.out.println("Digite la cantidad de kilos de ropa: ");
        System.out.println("Maximo 12 kilos");
        int kilos = sc.nextInt();
        
        Lavadora ls = new Lavadora(kilos, tipoRopa);
        ls.setTipoRopa(2);
        System.out.println("tipor de ropa es: "+ls.getTipoRopa());
        ls.cicloFinalizado();
    }
}