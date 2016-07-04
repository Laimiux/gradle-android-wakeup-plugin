package com.antyzero.wakeup.gradle;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WakeUpDeviceTest {

    private WakeUpDevice wakeUpDevice;

    @Before
    public void setUp() throws Exception {
        wakeUpDevice = new WakeUpDevice();
    }

    @After
    public void tearDown() throws Exception {
        wakeUpDevice.finish();
        wakeUpDevice = null;
    }

    @Test
    public void testWakeUp() throws Exception {

        // Given
        //...

        // When
        wakeUpDevice.wakeDevices();

        // Then
        // our device should wake up and unlock if possible
    }
}
