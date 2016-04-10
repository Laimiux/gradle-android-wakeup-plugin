package com.antyzero.wakeup.gradle;


import com.android.ddmlib.*;

import java.io.IOException;

public class WakeUpDevice {

    public WakeUpDevice() {
        AndroidDebugBridge.init(false);
    }

    public void finish(){
        AndroidDebugBridge.terminate();
    }

    public void wakeDevices(){

        AndroidDebugBridge androidDebugBridge = AndroidDebugBridge.createBridge();

        try {

            for(IDevice device :  androidDebugBridge.getDevices()){
                device.executeShellCommand("", null);
            }

        } catch (TimeoutException | AdbCommandRejectedException | IOException | ShellCommandUnresponsiveException e) {
            e.printStackTrace();
        } finally {
            AndroidDebugBridge.disconnectBridge();
        }
    }
}
