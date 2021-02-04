public class PinsetterEvent {

	private boolean[] pinsStillStanding;
	private boolean foulCommited;
	private int throwNumber;
	private int pinsDownThisThrow;
	final int NUM_PINS=9;

	/** PinsetterEvent()
	 *
	 * creates a new pinsetter event
	 *
	 * @pre none
	 * @post the object has been initialized
	 */
	public PinsetterEvent(boolean[] current_pin_status, boolean foul, int tn, int pinsDownThisThrow) {

		pinsStillStanding = new boolean[10];


		for(int i=0 ; i < NUM_PINS;i++)
		{
			pinsStillStanding[i] = current_pin_status[i];
		}

		foulCommited = foul;
		throwNumber = tn;
		this.pinsDownThisThrow = pinsDownThisThrow;
	}

	/** pinKnockedDown()
	 *
	 * check if a pin has been knocked down
	 *
	 * @return true if pin [i] has been knocked down
	 */
	public boolean pinKnockedDown(int i) {
		return !pinsStillStanding[i];
	}

	/** pinsDownOnThisThrow()
	 *
	 * @return the number of pins knocked down assosicated with this event
	 */
	public int pinsDownOnThisThrow() {
		return pinsDownThisThrow;
	}

	/** totalPinsDown()
	 *
	 * @return the total number of pins down for pinsetter that generated the event
	 */
	public int totalPinsDown() {
		int count = 0;

		for (int i=0; i <= NUM_PINS; i++) {
			if (pinKnockedDown(i)) {
				count++;
			}
		}

		return count;
	}

	/** isFoulCommited()
	 *
	 * @return true if a foul was commited on the lane, false otherwise
	 */
	public boolean isFoulCommited() {
		return foulCommited;
	}

	/** getThrowNumber()
	 *
	 * @return current number of throws taken on this lane after last reset
	 */
	public int getThrowNumber() {
		return throwNumber;
	}
}

