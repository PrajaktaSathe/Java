public class NoDupArray {

    public static void main(String[] args) {
       NoDupArray x=new NoDupArray(6);
       x.insert(9);
        x.insert(7);
         x.insert(3);
          x.insert(8);
          x.display();
          x.insert(8);
          x.delete(8);
           x.display();
          System.out.println("find the value 3 :"+x.find(3)) ;
    }
    
}

class NoDupArray{
    private long[] a;
    private int nElements;
    
    
    public NoDupArray(int max){
            this.a=new long[max];
            this.nElements=0;
    }
    
    //search value
    public boolean find(long SearchKey) {//[9,7,6,2,_,_]
        
        for(int i=0;i<this.nElements;i++){
            if(this.a[i]==SearchKey){
                return true;
            }
        }
        return false;
    }
    
    public void insert(long value) {//put element into array
        
        
        if(find(value)){
            System.out.println("Value already exists.");
        }else{
            a[this.nElements]=value;
            this.nElements++;
        }
    }
    
    
    public boolean delete(long value) {//delete the element if it found
          for(int i=0;i<this.nElements;i++){
              if(this.a[i]==value){
                  for(int j=i; j<this.nElements-1;i++){
                      this.a[j]=this.a[j+1];                
                  }
              this.nElements--;
              System.out.println("Value Deleted");
              return true;
              }
              
          }
          return false;//value is not found for deletion
    }
    
    
    public void display() {//display array contents
        for(int i=0;i<this.nElements;i++){
            System.out.print(this.a[i]+" ");
        }
        System.out.println("");
        
        
 } 
}
