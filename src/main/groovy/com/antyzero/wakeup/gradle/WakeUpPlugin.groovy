package com.antyzero.wakeup.gradle

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.api.TestVariant
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaBasePlugin

class WakeUpPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        if (!project.plugins.findPlugin(AppPlugin) && !project.plugins.findPlugin(LibraryPlugin)) {
            throw new IllegalStateException("Android plugin is not found")
        }

        def wakeUpTask = project.task("wakeUp", type: WakeUpRunTask) {
            group = JavaBasePlugin.CHECK_TASK_NAME
            description = "Wake up (and unlock if possible) all connected devices"
        }

        BaseExtension android = project.android

        android.testVariants.all { TestVariant variant ->
            variant.connectedInstrumentTest.dependsOn wakeUpTask
        }
    }
}
