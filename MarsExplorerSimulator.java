public class MarsExplorerSimulator
{
	private String place;
	private String move;

	public MarsExplorerSimulator(String place)
	{
		this.place = place;
		this.move = "";
	}

	public String getPlace()
	{
		return place;
	}

	public String getMove()
	{
		return move;
	}

	public void updatePlace(String newPlace)
	{
		place = newPlace;
	}

	public void updateMove(String newMove)
	{
		move += "(" + newMove + ")";
	}

	public String toString()
	{
		String journey = "";
		if(move != "")
		{
			journey += "M:" + move;
		}

		journey += "\nP:(" + place + ")";

		return journey;
	}
}
