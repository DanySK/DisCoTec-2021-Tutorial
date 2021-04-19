import de.fayard.dependencies.bootstrapRefreshVersionsAndDependencies

buildscript {
    repositories { gradlePluginPortal() }
    dependencies.classpath("de.fayard:dependencies:+")
}

bootstrapRefreshVersionsAndDependencies()

rootProject.name = "discotec-2021-alchemist-tutorial"
