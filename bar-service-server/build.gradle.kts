import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    application
    kotlin("jvm")
    id("com.google.protobuf")
}

dependencies {
    protobuf(project(":bar-service-models"))

    api(kotlin("stdlib"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")

    api("io.grpc:grpc-protobuf:${rootProject.ext["grpcVersion"]}")
    api("com.google.protobuf:protobuf-java-util:${rootProject.ext["protobufVersion"]}")
    api("io.grpc:grpc-kotlin-stub:${rootProject.ext["grpcKotlinVersion"]}")
    runtimeOnly("io.grpc:grpc-netty:${rootProject.ext["grpcVersion"]}")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

sourceSets {
    val main by getting { }
    main.java.srcDirs("build/generated/source/proto/main/grpc")
    main.java.srcDirs("build/generated/source/proto/main/grpckt")
    main.java.srcDirs("build/generated/source/proto/main/java")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${rootProject.ext["protobufVersion"]}"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${rootProject.ext["grpcVersion"]}"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:${rootProject.ext["grpcKotlinVersion"]}:jdk7@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}

tasks.register<JavaExec>("start") {
    dependsOn("classes")
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.hypto.hws.services.StarterServerKt")
}

val starterServerStartScripts = tasks.register<CreateStartScripts>("starterServerStartScripts") {
    mainClass.set("com.hypto.hws.services.StarterServerKt")
    applicationName = "starter-server"
    outputDir = tasks.named<CreateStartScripts>("startScripts").get().outputDir
    classpath = tasks.named<CreateStartScripts>("startScripts").get().classpath
}

tasks.named("startScripts") {
    dependsOn(starterServerStartScripts)
}
