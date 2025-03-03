import com.android.build.api.dsl.ApplicationExtension
import com.cybozu.android.callout.convention.android
import com.cybozu.android.callout.convention.configureAndroidCommon
import com.cybozu.android.callout.convention.configureKotlinCommon
import com.cybozu.android.callout.convention.configureLicenseCheck
import com.cybozu.android.callout.convention.configureLint
import com.cybozu.android.callout.convention.getPluginId
import com.cybozu.android.callout.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.getPluginId("android-application"))
                apply(libs.getPluginId("kotlin-android"))
                apply(libs.getPluginId("dokka"))
            }

            configureKotlinCommon()
            configureLicenseCheck()
            configureLint()

            android<ApplicationExtension> {
                configureAndroidCommon(this)
                defaultConfig {
                    targetSdk = 35
                }
            }
        }
    }
}