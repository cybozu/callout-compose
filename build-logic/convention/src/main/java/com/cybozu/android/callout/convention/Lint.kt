package com.cybozu.android.callout.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension

fun Project.configureLint() {
    pluginManager.apply(libs.getPluginId("ktlint-gradle"))

    ktlint {
        android = true
        version = libs.getVersion("ktlint")
    }
}

private fun Project.ktlint(action: KtlintExtension.() -> Unit) = configure(action)