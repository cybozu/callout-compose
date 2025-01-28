import com.cybozu.android.callout.convention.configureJetpackComposeCore
import org.gradle.api.Plugin
import org.gradle.api.Project

class JetpackComposeCorePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureJetpackComposeCore()
        }
    }
}