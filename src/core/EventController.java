/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 * An EventController describes behavior based on received events.
 * @author ajc
 */
public abstract class EventController extends GRTLoggedProcess {

    public EventController(String name) {
        super(name);
        running = true; //TODO does this belong
    }

    /**
     * Starts listening to events
     */
    protected abstract void startListening();

    /**
     * Stops listening to events
     */
    protected abstract void stopListening();

    /**
	 * Enables actions, and begins listening
	 */
    public void enable() {
        //enable() always works because an EventController is always running
        super.enable();
        startListening();
    }

	/**
	 * Disables actions, and stops listening
	 */
    public void disable() {
        super.disable();
        stopListening();
    }
}
