import java.util.Vector;
import java.io.*;


public class drive {

	public static final int NUM_LANES = 3;
	public static final int MAX_PATRONS_PER_PARTY = 5;

	public static void main(String[] args) {

//		int NUM_LANES = NUM_LANES;

		Alley a = new Alley( NUM_LANES );
		ControlDesk controlDesk = a.getControlDesk();

		ControlDeskView cdv = new ControlDeskView( controlDesk, MAX_PATRONS_PER_PARTY);
		controlDesk.subscribe( cdv );

	}
}
