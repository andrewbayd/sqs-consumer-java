plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("javax.jms:javax.jms-api:2.0.1")
    implementation("com.amazonaws:amazon-sqs-java-messaging-lib:2.1.1")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("org.apache.logging.log4j:log4j-core:2.22.0")
    implementation("org.apache.logging.log4j:log4j-api:2.22.0")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.22.0")
}