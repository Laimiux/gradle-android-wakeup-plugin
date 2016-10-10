package com.antyzero.wakeup.gradle;


import com.android.ddmlib.*;

import java.io.File;
import java.io.IOException;

public class WakeUpDevice {

    private final String sdkAndroid;

    private NullOutputReceiver outputReceiver = new NullOutputReceiver();

    public WakeUpDevice() {
        this(null);
    }

    public WakeUpDevice(File file) {
        this.sdkAndroid = file != null ? file.toString() : null;
        AndroidDebugBridge.initIfNeeded(false);
    }

    public void finish() {
        AndroidDebugBridge.terminate();
    }

    public void wakeDevices() {

        AndroidDebugBridge androidDebugBridge = sdkAndroid == null ?
                AndroidDebugBridge.createBridge() :
                AndroidDebugBridge.createBridge(sdkAndroid + "/platform-tools/adb", false);

        try {
            IDevice[] devices = requestDevices(androidDebugBridge, 10);
            System.out.println(String.format("Wa have %d connected device(s)", devices.length));

            for (IDevice device : devices) {
                try {
                    wakeDevice(device);
                } catch (Exception e) {
                    System.out.println(String.format("Unable to wake device %s", device));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            AndroidDebugBridge.disconnectBridge();
        }
    }

    private void wakeDevice(IDevice device) throws TimeoutException, AdbCommandRejectedException, ShellCommandUnresponsiveException, IOException, InterruptedException {
        device.executeShellCommand("input keyevent KEYCODE_WAKEUP", outputReceiver);
        Thread.sleep(500L);
        device.executeShellCommand("input keyevent 82", outputReceiver);
        Thread.sleep(500L);
        device.executeShellCommand("input keyevent 4", outputReceiver);
        Thread.sleep(500L);
        device.executeShellCommand("input keyevent 3", outputReceiver);
        Thread.sleep(500L);
        device.executeShellCommand("input keyevent 4", outputReceiver);
    }

    /**
     * Get currently connected devices info
     *
     * @param androidDebugBridge for devices list
     * @param tries              to get devices list
     * @return IDevice array
     * @throws InterruptedException
     */
    private IDevice[] requestDevices(AndroidDebugBridge androidDebugBridge, int tries) throws InterruptedException {

        System.out.println(String.format("ADB Connected: %b", androidDebugBridge.isConnected()));

        IDevice[] iDevices = androidDebugBridge.getDevices();

        if (iDevices.length <= 0 && tries > 0) {
            Thread.sleep(5000L);
            return requestDevices(androidDebugBridge, tries - 1);
        }

        return iDevices;
    }
}
