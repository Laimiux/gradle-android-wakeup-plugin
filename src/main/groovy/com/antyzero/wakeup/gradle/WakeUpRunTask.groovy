package com.antyzero.wakeup.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


class WakeUpRunTask extends DefaultTask {

    @TaskAction
    void runWakeUp() {
        //def wakeUpDevice = new WakeUpDevice(project.android.sdkDirectory);
        def wakeUpDevice = new WakeUpDevice();
        wakeUpDevice.wakeDevices()
        wakeUpDevice.finish()
    }
}
