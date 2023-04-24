


public class ADTFractionApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ADTFraction f1=new ADTFraction(3,5);
       f1.display();
       ADTFraction f2=new ADTFraction(7,8);
       f2.display();
    }
    
}


class ADTFraction {
 private int n; //numerator
 private int d; //denomenator
//---------------------------------------------------
 public ADTFraction() {//default constructor
 this.n=0;
 this.d=1;
 }
//---------------------------------------------------
 public ADTFraction(int a, int b) {//parameter constructor

if(b!=0){
    this.d=b;
    this.n=a;
}
else{
    this.n=0;
    this.d=1;
    System.out.println("Denomenator cannot be Zero");
}
     
     
 }
//---------------------------------------------------
 public void set(int a, int b) {//set numerator and denomenator
     if(b!=0){
    this.d=b;
    this.n=a;
}
else{
    this.n=0;
    this.d=1;
    System.out.println("Denomenator cannot be Zero");
}
 }
 
 
 
//---------------------------------------------------
 public ADTFraction plus(ADTFraction x) {//add two fractions   this=3/5  x=7/8
     int num, den;
     den=this.d * x.d;
     num=this.n * x.d + x.n * this.d;
     ADTFraction f1 = new ADTFraction(num,den);
     return f1;
 }
 
 
//---------------------------------------------------
 public ADTFraction times(int a) {//multiply fraction by a number
     int num, den;
     den=this.d;
     num=this.n * a;
     
     ADTFraction f1 = new ADTFraction(num,den);
     return f1;
     
     //return times(new ADTFraction(a,1))
     
 }
 
 
 //---------------------------------------------------
 public ADTFraction times(ADTFraction x) {//multiply two fractions
    int num, den;
     den=this.d * x.d;
     num=this.n * x.n;
     
     ADTFraction f1 = new ADTFraction(num,den);
     return f1;
 }
 
 
//---------------------------------------------------
 public ADTFraction reciprocal() {//reciprocal of a fraction
   ADTFraction f1=new  ADTFraction(this.d,this.n);
   return f1;
 }
 
 
//---------------------------------------------------
 public float value() {//numerical value of a fraction
    return (float)this.n/this.d;
 }
 
 
//---------------------------------------------------
 public void display() {//display the fraction in the format n/d
     System.out.println(this.n + "/" + this.d);
 }
//---------------------------------------------------

}