plugins {
    `kotlin-dsl`
    alias(libs.plugins.ktlint.gradle)
}

group = "com.cybozu.android.callout.compose.buildlogic"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.licensee.gradle.plugin)
    compileOnly(libs.ktlint.gradle.plugin)
    compileOnly(libs.roborazzi.gradle.plugin)
}

ktlint {
    version = libs.versions.ktlint
}

gradlePlugin {
    plugins {
    }
}
