class Sample{
    Sample(){
        System.out.println("Object created");
    }

    protected void finalize(){
        System.out.println("Object destroyed");
    }
}
public class GarbageCollect {
    public static void main(String[] args) {
        Sample obj=new Sample();
        obj=null;
        Sample one=new Sample();
        Sample two=new Sample();
        one=two;
       
        System.gc();
    }
}