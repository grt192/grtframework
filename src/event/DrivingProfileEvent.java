/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import sensor.base.GRTDriverStation;
import sensor.base.IDriverProfile;

/**
 *
 * @author ajc
 */
public class DrivingProfileEvent {

    private final GRTDriverStation source;
    private final IDriverProfile profile;

    public DrivingProfileEvent(GRTDriverStation source, IDriverProfile profile) {
        this.source = source;
        this.profile = profile;

    }

    public IDriverProfile getProfile() {
        return profile;
    }

    public GRTDriverStation getSource() {
        return source;
    }
}
