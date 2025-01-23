plugins {
    id("com.gradle.enterprise") version "3.19.1"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishOnFailure()
    }
}

rootProject.name = "discotec-2021-alchemist-tutorial"
