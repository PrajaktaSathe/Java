/*
 *Se ha creado la clase Lavadora para realizar los metodos requeridos del funcionamiento de una lavadora,
 utilizando la encapsulacion correcta de metodos y variables.
 *Se solicito que el unico metodo visible sea el metodo 
 cicloFinalizado().

 *Instrucciones del programa:
 *La variable kilosRopa puede cambiar y se digitaran numeros de 0 hasta 12
 *La variable tipoRopa puede ser modificada con dos opciones: 1 - ropa blanca | 2 - ropa color
 *Las variables llenado, lavado, secado son modificadas de 0 a 1 para verificar si esos procesos son realizados
  con exito
*/

public class Lavadora {
    
    private int kilosRopa = 0, tipoRopa = 0, llenado = 0, lavado = 0, secado = 0;
    
    //Constructor del programa 
    public Lavadora(int kilosRopa, int tipoRopa){
        this.kilosRopa = kilosRopa;
        this.tipoRopa = tipoRopa;
    }
    
    //Metodo llenadoAgua que valida la cantidad de kilos de ropa digitados
    private void llenadoAgua(){
        if(kilosRopa <= 12){
            llenado = 1;
            System.out.println("Llenando...");
            System.out.println("Llenado");
        }else{
            System.out.println("Carga de ropa elevada, reduzca la carga");
        }
    }
    
    //Metodo de Lavado que realiza la validacion de tipo de ropa
    private void Lavado(){
        llenadoAgua();
        if(llenado == 1){
            if(tipoRopa == 1){
                System.out.println("Ropa blanca / Lavado suave");
                System.out.println("Lavando...");
                lavado = 1;
            }else if(tipoRopa == 2){
                System.out.println("Ropa Color / Lavado intenso");
                System.out.println("Lavando...");
                lavado = 1;
            }else{
                System.out.println("El tipo de ropa es incorrecto");
                System.out.println("Se tratara como Ropa Color / Lavado Intenso");
                lavado = 1;
            }
        }else{
            System.out.println("No se ha llenado la lavadora, no se puede lavar");
        }
    }
    
    //Metodo Secado que verifica si se realizo el lavado y envia un mensaje por consola
    private void Secado(){
        Lavado();
        if(lavado == 1){
            System.out.println("Secando...");
            secado = 1;
        }
    }
    
    //Metodo visible que confirma si se realizaron correctamente los otros procesos
    public void cicloFinalizado(){
        Secado();
        if(secado == 1){
            System.out.println("El lavado y secado se ha completado");
        }
    }
    
    //Setter y Getter
    
    public int getTipoRopa(){
        return tipoRopa;
    }
    
    public void setTipoRopa(int tipoRopa){
        this.tipoRopa = tipoRopa;
    }
    
}