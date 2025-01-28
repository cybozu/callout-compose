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
        register("androidApplication") {
            id = libs.plugins.callout.compose.android.application.get().pluginId
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.callout.compose.android.library.get().pluginId
            implementationClass = "AndroidLibraryPlugin"
        }
        register("jetpackComposeCore") {
            id = libs.plugins.callout.compose.jetpack.compose.core.get().pluginId
            implementationClass = "JetpackComposeCorePlugin"
        }
        register("screenshotTest") {
            id = libs.plugins.callout.compose.screenshot.test.get().pluginId
            implementationClass = "ScreenshotTestPlugin"
        }
    }
}
