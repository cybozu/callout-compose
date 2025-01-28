import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.BaseExtension
import com.cybozu.android.callout.convention.android
import com.cybozu.android.callout.convention.applyIfAndroidModule
import com.cybozu.android.callout.convention.getPluginId
import com.cybozu.android.callout.convention.libs
import io.github.takahirom.roborazzi.RoborazziExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class ScreenshotTestPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyIfAndroidModule {
                configureScreenshotTest()
            }
        }
    }

    private fun Project.configureScreenshotTest() {
        pluginManager.apply(libs.getPluginId("roborazzi"))

        roborazzi {
            generateComposePreviewRobolectricTests {
                enable = true
                includePrivatePreviews = true
                packages.set(
                    provider {
                        listOf(namespace)
                    }
                )
            }
        }

        android<CommonExtension<*, *, *, *, *, *>> {
            testOptions {
                unitTests {
                    isIncludeAndroidResources = true
                    all {
                        it.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware"
                    }
                }
            }
        }

        dependencies {
            add("testImplementation", libs.findLibrary("junit").get())
            add("testImplementation", libs.findLibrary("robolectric").get())
            add("testImplementation", libs.findLibrary("composable-preview-scanner").get())
            add("testImplementation", libs.findLibrary("roborazzi-compose-preview").get())
        }
    }

    private val Project.namespace
        get() = checkNotNull(project.extensions.findByType(BaseExtension::class.java)?.namespace) {
            "Namespace is not defined in the Android extension."
        }
}

private fun Project.roborazzi(action: RoborazziExtension.() -> Unit) = configure(action)