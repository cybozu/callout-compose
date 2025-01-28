plugins {
    alias(libs.plugins.callout.compose.android.library)
    alias(libs.plugins.callout.compose.jetpack.compose.core)
}

android {
    namespace = "com.cybozu.android.callout.compose.material2"
}

dependencies {
    implementation(project(":core"))
    implementation(libs.androidx.material2)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
