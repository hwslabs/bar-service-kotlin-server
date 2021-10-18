rootProject.name = "bar-service-kotlin-server"

include("bar-service-models", "bar-service-server")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}
