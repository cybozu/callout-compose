package com.cybozu.android.callout.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun VersionCatalog.getPluginId(alias: String): String = findPlugin(alias).get().get().pluginId

fun VersionCatalog.getVersion(alias: String): String = findVersion(alias).get().requiredVersion