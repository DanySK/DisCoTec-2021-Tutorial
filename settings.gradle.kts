import org.danilopianini.VersionAliases.justAdditionalAliases

plugins {
    id("de.fayard.refreshVersions") version "0.10.1"
}

refreshVersions {
    extraArtifactVersionKeyRules = justAdditionalAliases
}

buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies {
        classpath("org.danilopianini:refreshversions-aliases:+")
    }
}

rootProject.name = "discotec-2021-alchemist-tutorial"
