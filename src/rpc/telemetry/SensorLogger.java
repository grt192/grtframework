/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpc.telemetry;

import core.EventController;
import core.Sensor;
import event.SensorChangeListener;
import event.SensorEvent;
import rpc.RPCConnection;
import rpc.RPCMessage;

/**
 * Automatically sends all data from any sensor.
 * @author ajc
 */
public class SensorLogger extends EventController implements SensorChangeListener {

    private final Sensor s;
    private final RPCConnection conn;
    private final int[] rpcKeys;

    /**
     * 
     * @param s sensor to read from
     * @param conn connection to send data with
     * @param rpcKeys rpc keys to send data with for each index of sensor
     * @param name name of process
     */
    public SensorLogger(Sensor s, RPCConnection conn, int[] rpcKeys, String name) {
        super(name);
        this.s = s;
        this.conn = conn;
        this.rpcKeys = rpcKeys;
    }

    protected void startListening() {
        s.addSensorStateChangeListener(this);
    }

    protected void stopListening() {
        s.removeSensorStateChangeListener(this);
    }

    public void sensorStateChanged(SensorEvent e) {
        conn.send(new RPCMessage(rpcKeys[e.getId()], e.getData()));
    }
}
