package com.cybozu.android.callout.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.configureJetpackComposeCore() {
    dependencies {
        add("implementation", libs.findLibrary("androidx-core-ktx").get())
        add("implementation", libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
        add("implementation", libs.findLibrary("androidx-activity-compose").get())
        add("implementation", platform(libs.findLibrary("androidx-compose-bom").get()))
        add("implementation", libs.findLibrary("androidx-ui").get())
        add("implementation", libs.findLibrary("androidx-ui-graphics").get())
        add("implementation", libs.findLibrary("androidx-ui-tooling-preview").get())

        add("debugImplementation", libs.findLibrary("androidx-ui-tooling").get())
        add("debugImplementation", libs.findLibrary("androidx-ui-test-manifest").get())

        add("androidTestImplementation", platform(libs.findLibrary("androidx-compose-bom").get()))
        add("androidTestImplementation", libs.findLibrary("androidx-ui-test-junit4").get())
    }
}