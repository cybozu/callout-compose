import com.android.build.api.dsl.LibraryExtension
import com.cybozu.android.callout.convention.android
import com.cybozu.android.callout.convention.configureAndroidCommon
import com.cybozu.android.callout.convention.configureKotlinCommon
import com.cybozu.android.callout.convention.configureLicenseCheck
import com.cybozu.android.callout.convention.configureLint
import com.cybozu.android.callout.convention.explicitApi
import com.cybozu.android.callout.convention.getPluginId
import com.cybozu.android.callout.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.getPluginId("android-library"))
                apply(libs.getPluginId("kotlin-android"))
                apply(libs.getPluginId("dokka"))
            }

            configureKotlinCommon()
            explicitApi()
            configureLicenseCheck()
            configureLint()

            android<LibraryExtension> {
                configureAndroidCommon(this)
            }
        }
    }
}
