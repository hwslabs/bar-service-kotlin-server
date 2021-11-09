rootProject.name = "{TEMPLATE_SERVICE_HYPHEN_NAME}-service-server"

include("{TEMPLATE_SERVICE_HYPHEN_NAME}-service-models", "{TEMPLATE_SERVICE_HYPHEN_NAME}-service-server")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}
