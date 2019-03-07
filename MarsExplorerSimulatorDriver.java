import java.io.* ;
import java.util.Scanner;

public class MarsExplorerSimulatorDriver
{
	public static void main(String[] args)
	{
		MarsExplorerSimulatorDriver mesd = new MarsExplorerSimulatorDriver();		
		mesd.start();
	}

	public void start()
	{
		MarsExplorerSimulator apollo1 = new MarsExplorerSimulator();
		// Create a scanner to read in from a file titled "script.txt"
		try
		{
			File file = new File("scripts.txt");
			Scanner sc = new Scanner(file);

			// While the file has more lines keep reading in
			while(sc.hasNextLine())
			{
				String getCommand = sc.nextLine();
				if(getCommand.equalsIgnoreCase("REPORT"))
				{
					report(apollo1);
				}
				else
				{
					String[] inputArray = getCommand.split(" ");
					if(acceptableRange(inputArray[1]))
					{
						
						if(inputArray[0].equalsIgnoreCase("PLACE"))
						{
							place(apollo1, inputArray[1]);
						}
						else
						{
							move(apollo1, inputArray[1]);
						}
					}
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File doesn't exist");
		}
	}

	public void report(MarsExplorerSimulator apollo1)
	{
		// If the place of the mars explorer is not an empty
		// string print out the toString method.
		if(apollo1.getPlace() != "")
		{
			System.out.println(apollo1);
		}
		else
		{
			System.out.println("\nThe Mars Explorer is not on the table and therefore no report can be made");
		}
	}

	public void place(MarsExplorerSimulator apollo1, String xyCoordinates)
	{				
		apollo1.updatePlace(xyCoordinates);		
	}

	public void move(MarsExplorerSimulator apollo1, String xyCoordinates)
	{
		
	}

	public boolean acceptableRange(String xyCoordinates)
	{
		// Get the x and y coordinates and check if they are within the
		// acceptable range.

		boolean acceptable = false;
		String[] coordinates = xyCoordinates.split(",");
		int xValue = Integer.parseInt(coordinates[0]);
		int yValue = Integer.parseInt(coordinates[1]);

		if(xValue <= 5 && xValue >= 0 && yValue <= 5 && yValue >= 0)
		{
			acceptable = true;
		}
		else
		{
			System.out.println("The X and Y coordinates are not withing the acceptable range. Please make sure that they are no less than 0, and no greater than 5.");
		}

		return acceptable;
	}
}