package com.cybozu.android.callout.convention

import app.cash.licensee.LicenseeExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.configureLicenseCheck() {
    pluginManager.apply(libs.getPluginId("licensee"))

    licensee {
        allow("Apache-2.0")
        allow("MIT")
    }
}

private fun Project.licensee(action: LicenseeExtension.() -> Unit) = configure(action)