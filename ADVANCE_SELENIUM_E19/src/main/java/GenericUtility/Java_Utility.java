package GenericUtility;

import java.util.Random;

public class Java_Utility
{
	/**
	 * The Method is used to avoid duplicate values
	 * 
	 * @return
	 * @author Anju
	 */
	
	public int getRandomNum()
	{
		Random ran = new Random(); 
		int ranNum = ran.nextInt(1000);
		return ranNum;
		
	}

}
