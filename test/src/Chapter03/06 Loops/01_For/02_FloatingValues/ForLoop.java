public class ForLoop 
{
   public static void main(String[] args) 
   {
      int limit = 20;                     // Sum from 1 to this value
      int sum = 0;                        // Accumulate sum in this variable

      // Loop from 1 to the value of limit, adding 1 each cycle
      for(double radius = 1.0; radius <= 2.0; radius += 0.2)
      {
        System.out.println("radius = " + radius + " area = " + Math.PI*radius*radius);
      }
   }
}
