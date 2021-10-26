rootProject.name = "bar-service-kotlin-server"

include("starter-service-models", "starter-service-server")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}
