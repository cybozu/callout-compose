package com.cybozu.android.callout.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign

fun Project.configureAndroidCommon(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    commonExtension.apply {
        compileSdk = 35

        defaultConfig {
            minSdk = 27
        }

        buildFeatures {
            buildConfig = true
        }
    }
}

fun Project.configureKotlinCommon() {
    kotlin {
        compilerOptions.apply {
            allWarningsAsErrors = propOrDefault(propertyName = "warningsAsErrors", defaultValue = "false").toBoolean()
        }
    }

    java {
        toolchain {
            languageVersion.set(org.gradle.jvm.toolchain.JavaLanguageVersion.of(21))
        }
    }
}

fun Project.explicitApi() {
    kotlin {
        explicitApi()
    }
}

private fun <T : Any> Project.propOrDefault(propertyName: String, defaultValue: T): T {
    @Suppress("UNCHECKED_CAST")
    val propertyValue = project.properties[propertyName] as T?
    return propertyValue ?: defaultValue
}