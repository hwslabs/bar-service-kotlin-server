rootProject.name = "bar-service-kotlin-server"

include("bar-service-models", "bar-service-kotlin-stubs", "bar-service-server")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}
