/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package sensor;

import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SensorBase;



/**
 *
 * digital accelerometer from the 2010 KOP
 *
 * this implementation just gets the 3 axis values and allows setting the range
 *
 * Possible enhancements:
 *      Add access to other features of the chip
 *      Add measurement of error during intialization and set channel offsets
 */
public class ADXL345DigitalAccelerometer extends SensorBase {
    private I2C i2c;
    // default address
    private static final byte kAddress = 0x3A;
    // register map from datasheet
    private static final byte OFSX = 0x1E;
    private static final byte OFSY = 0x1F;
    private static final byte OFSZ = 0x20;
    private static final byte BW_RATE = 0x2C;
    private static final byte  POWER_CTL = 0x2D;
    private static final byte DATA_FORMAT = 0x31;
    private static final byte DATAX0 = 0x32;
    private static final byte DATAY0 = 0x34;
    private static final byte DATAZ0 = 0x36;
    private static final byte FIFO_CTL = 0x38;
    private static final byte FIFO_STATUS = 0x39;

    
    
    
    // would use enums here if we had them
    // BW_RATE 0x2C
    private static final byte BW_RATE_R3200B1600 = 0x0F;
    private static final byte BW_RATE_R1600B0800 = 0x0E;
    private static final byte BW_RATE_R0800B0400 = 0x0D;
    private static final byte BW_RATE_R0400B0200 = 0x0C;
    private static final byte BW_RATE_R0200B0100 = 0x0B;
    private static final byte BW_RATE_R0100B0050 = 0x0A;
    private static final byte BW_RATE_R0050B0025 = 0x09;
    private static final byte BW_RATE_R0025B0012 = 0x08;
    private static final byte BW_RATE_R0012B0006 = 0x07;
    private static final byte BW_RATE_R0006B0003 = 0x06;

    private static final byte BW_RATE_LOW_POWER = 0x10;

    // POWER_CTL 0x2D
    private static final byte POWER_CTL_LINK = 0x20;
    private static final byte POWER_CTL_AUTO_SLEEP = 0x10;
    private static final byte POWER_CTL_MEASURE = 0x08;
    private static final byte POWER_CTL_SLEEP = 0x04;
    private static final byte POWER_CTL_WAKEUP8 = 0x00;
    private static final byte POWER_CTL_WAKEUP4 = 0x01;
    private static final byte POWER_CTL_WAKEUP2 = 0x02;
    private static final byte POWER_CTL_WAKEUP1 = 0x03;

    // DATA_FORMAT
    public static final byte DATA_FORMAT_02G = 0x00;
    public static final byte DATA_FORMAT_04G = 0x01;
    public static final byte DATA_FORMAT_08G = 0x02;
    public static final byte DATA_FORMAT_16G = 0x03;

    // store the current
    private byte range = DATA_FORMAT_02G;

    public class ADXL345Exception extends RuntimeException {

        /**
         * Create a new exception with the given message
         * @param message the message to pass with the exception
         */
        public ADXL345Exception(String message) {
            super(message);
        }

    }

    //
    // constuctior with slot number parameter
    //
    public ADXL345DigitalAccelerometer(int slot) {

        i2c = new I2C( DigitalModule.getInstance(1), kAddress );
    }

    // initialize the sensor
    public void initialize()
    {
        // set BW_RATE
        i2c.write(BW_RATE, BW_RATE_R0100B0050);
        // set POWER_CTL
        i2c.write(POWER_CTL, POWER_CTL_MEASURE);
    }

    // set he range (default is =/- 2g
    public void setRange( byte rangeParam )
    {
        if ( !( rangeParam == DATA_FORMAT_02G ||
                rangeParam == DATA_FORMAT_04G ||
                rangeParam == DATA_FORMAT_08G ||
                rangeParam == DATA_FORMAT_16G ) )
        {
            throw new ADXL345Exception("Invalid range!");
        }


        range = rangeParam;

        i2c.write(DATA_FORMAT, range);
    }

    // get acceleration routines
    public double getXAxis()
    {
        return getAxis( DATAX0 );
    }

    public double getYAxis()
    {
        return getAxis( DATAY0 );
    }

    public double getZAxis()
    {
        return getAxis( DATAZ0 );
    }

    protected double getAxis( byte registerParam )
    {
        // setup array for our data
        byte[] data = new byte[2];
        // read consecutive registers
        this.i2c.read( registerParam, (byte) data.length, data);

        // convert to 2s complement integer
        // [0] has low byte [1] has the high byte
        // jave does not have unsigned so we have to do it this way
        int intResult = ( data[0] & 0xFF ) | ( data[1] << 8 );

        // convert to double based on 10 bit result
        double returnValue = (double)intResult / 512.0 ;

        // now scale based upon our range
        switch( range )
        {
            case DATA_FORMAT_02G:
                returnValue *= 2.0;
                break;
            case DATA_FORMAT_04G:
                returnValue *= 4.0;
                break;
            case DATA_FORMAT_08G:
                returnValue *= 8.0;
                break;
            case DATA_FORMAT_16G:
                returnValue *= 16.0;
                break;
        }
        return returnValue;
    }
}
