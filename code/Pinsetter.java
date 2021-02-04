
/*
 * Class to represent the pinsetter
 *
 */

import java.util.*;
import java.lang.Boolean;

//Pinsetter class
public class Pinsetter {

	final int NUM_PINS=9;
	private Random rnd;
	private Vector<PinsetterObserver> subscribers;
	private boolean foul;
	private int throwNumber;

	private boolean[] pins;
			/* 0-9 of state of pine, true for standing,
			false for knocked down

			6   7   8   9
			  3   4   5
			    2   1
			      0

			*/

	/** sendEvent()
	 *
	 * Sends pinsetter events to all subscribers
	 *
	 * @pre none
	 * @post all subscribers have recieved pinsetter event with updated state
	 * */
	private void sendEvent(int jdpins) {	// send events when our state is changd
		for (Object subscriber : subscribers) {
			((PinsetterObserver) subscriber).receivePinsetterEvent(
					new PinsetterEvent(pins, foul, throwNumber, jdpins));
		}
	}

	/** Pinsetter()
	 *
	 * Constructs a new pinsetter
	 *
	 * @pre none
	 * @post a new pinsetter is created
	 * @return Pinsetter object
	 */
	public Pinsetter() {
		pins = new boolean[10];
		rnd = new Random();
		subscribers = new Vector<PinsetterObserver>();
		foul = false;
		reset();
	}

	/** ballThrown()
	 *
	 * Called to simulate a ball thrown coming in contact with the pinsetter
	 *
	 * @pre none
	 * @post pins may have been knocked down and the thrownumber has been incremented
	 */
	public void ballThrown() {	// simulated event of ball hits sensor
		int count = 0;
		foul = false;
		double skill = rnd.nextDouble();

		for(int i = 0;i < NUM_PINS ; i++)
		{
			if(pins[i] == true)
			{
				double pin_probability = rnd.nextDouble();

				if(pin_probability <= 0.04) {
					foul = true;
				}

				if(((skill + pin_probability) * 0.6) > 0.5)// 0.6 because average of luck and skill times 1.2
				{
					pins[i] = false;
				}
				if(!pins[i])
				{
					count++;
				}
			}
		}

		try {
			Thread.sleep(500);				// pinsetter is where delay will be in a real game
		} catch (Exception e) {
			e.printStackTrace();
		}
		sendEvent(count);

		throwNumber++;
	}

	/** reset()
	 *
	 * Reset the pinsetter to its complete state
	 *
	 * @pre none
	 * @post pinsetters state is reset
	 */
	public void reset() {
		foul = false;
		throwNumber = 1;
		resetPins();

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sendEvent(-1);
	}

	/** resetPins()
	 *
	 * Reset the pins on the pinsetter
	 *
	 * @pre none
	 * @post pins array is reset to all pins up
	 */
	public void resetPins() {
		for (int i=0; i <= NUM_PINS; i++) {
			pins[i] = true;
		}
	}

	/** subscribe()
	 *
	 * subscribe objects to send events to
	 *
	 * @pre none
	 * @post the subscriber object will recieve events when their generated
	 */
	public void subscribe(PinsetterObserver subscriber) {
		subscribers.add(subscriber);
	}

}

