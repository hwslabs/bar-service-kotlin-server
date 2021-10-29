rootProject.name = "starter-service-server"

include("starter-service-models", "starter-service-server")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}
