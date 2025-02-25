package com.cybozu.android.callout.convention

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.configureLint() {
    pluginManager.apply(libs.getPluginId("ktlint-gradle"))
    pluginManager.apply(libs.getPluginId("spotless-gradle"))

    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            ktlint(libs.getVersion("ktlint"))
        }
        format("kts") {
            target("**/*.kts")
            targetExclude("**/build/**/*.kts")
        }
        format("xml") {
            target("**/*.xml")
            targetExclude("**/build/**/*.xml")
        }
    }
}

private fun Project.spotless(action: SpotlessExtension.() -> Unit) = configure(action)