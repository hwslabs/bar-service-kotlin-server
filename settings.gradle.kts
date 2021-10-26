rootProject.name = "bar-service-kotlin-server"

include("starter-service-models", "bar-service-server")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}
