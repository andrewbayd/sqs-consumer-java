plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("software.amazon.awssdk:sqs:2.20.138")
}

tasks.test {
    useJUnitPlatform()
}