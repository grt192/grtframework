package core;

public abstract class IterativeController extends GRTLoggedProcess {

	public IterativeController(String name) {
		super(name);
	}
	
	/**
	 * Performs one iteration cycle
	 */
	public abstract void iterate();

	
	/**
	 * Overrides GRTLoggedProcess.poll(); 
	 * When it's time to poll, we do another
	 * iteration cycle.
	 */
	public void poll(){
		iterate();
	}
	
    /**
     * Enables actions, and begins listening
     */
    public void enable() {
        //enable() always works because an EventController is always running
        super.enable();
        startPolling();
    }

    /**
     * Disables actions, and stops listening
     */
    public void disable() {
        super.disable();
    }

}
