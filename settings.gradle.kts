plugins {
    id("com.gradle.enterprise") version "3.12.4"
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishOnFailure()
    }
}

enableFeaturePreview("VERSION_CATALOGS")
rootProject.name = "discotec-2021-alchemist-tutorial"
