/**
 * 
 */

/**
 * @author Steve
 *
 */
public class WeatherFan {

	/**
	 * 
	 */
	public WeatherFan() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float [][]temperature = new float[10][365];
		
		for (int i = 0; i < temperature.length; i++)
		{
			for (int j = 0; j < temperature[0].length; j++)
			{
				temperature[i][j] = (float)(45.0*Math.random() - 10.0);
			}			
		}
				
		for (int i = 0; i < temperature.length; i++)
		{
			float average = 0.0f;
			
			for (int j = 0; j < temperature[0].length; j++)
			{
				average += temperature[i][j];
			}
			
			System.out.println("Average temperature at location " + (i+1) + " = " + average/(float)temperature[0].length);
		}		
	}

}
