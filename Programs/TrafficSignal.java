package Programs;

class TrafficSignal
{
      private String colour;
      
      private TrafficSignal(String colour)
      {
            this.colour=colour;
      }
      private boolean isRed()
      {
            return colour.equals("red");
      }
      private boolean isYellow()
      {
            return colour.equals("yellow");
      }
      private boolean isGreen()
      {
            return colour.equals("green");
      }
      
      //Driver Code
      public static void main(String[] args)
      {
            TrafficSignal light=new TrafficSignal("red");
            
            while(true)
            {
                  if(light.isRed())
                  {
                        System.out.println("The Signal is red.");
                        light.colour = "yellow";
                  }
                  else if(light.isYellow())
                  {
                        System.out.println("The Signal is yellow.");
                        light.colour = "green";
                  }
                  else if(light.isGreen())
                  {
                        System.out.println("The Signal is green.");
                        light.colour = "red";
                  }
                  for(int i = 10; i > 0; i--)
                  {
                        try{
                              System.out.print(" "+i);
                              Thread.sleep(1000);
                        }catch(InterruptedException e){
                              System.out.println("Delay not applied.");
                        }
                  }
                  System.out.println();
                  System.out.print("\033[H\033[2J");
                  System.out.flush();
            }
      }
}