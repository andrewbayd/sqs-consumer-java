plugins {
    java
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("io.awspring.cloud:spring-cloud-aws-starter:3.0.3")
    implementation("io.awspring.cloud:spring-cloud-aws-sqs:3.0.3")
}

tasks.test {
    useJUnitPlatform()
}