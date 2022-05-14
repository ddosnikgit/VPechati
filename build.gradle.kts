plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow")
}

repositories {
    mavenCentral()
}

group = "dev.ddosnik"
version = "1.0-SNAPSHOT"

dependencies {
   implementation("com.googlecode.json-simple:json-simple:1.1.1")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))

    withSourcesJar()
    // withJavadocJar() //
}

tasks {
    build { dependsOn(shadowJar) }
    shadowJar {
        archiveFileName.set("VPechati.jar")
        transform(com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer::class.java)

        manifest {
            attributes["Main-Class"] = "ru.ddosnik.VPechati"
            attributes["Multi-Release"] = "true"
        }
    }
    withType<JavaCompile> { options.encoding = "UTF-8" }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "vpechati"
            version = project.version.toString()

            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
    }
}
