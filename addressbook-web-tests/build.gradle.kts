plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val allureVersion = "2.29.0"
val aspectJVersion = "1.9.22"

configurations {
    create("agent") {
        isCanBeResolved = true
        isCanBeConsumed = true
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.29.0")
    implementation ("org.jcommander:jcommander:2.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("com.mysql:mysql-connector-j:9.2.0")
    implementation("org.hibernate.orm:hibernate-core:6.6.11.Final")
    "agent"("org.aspectj:aspectjweaver:$aspectJVersion")
    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-junit5")
}

tasks.test {
    useJUnitPlatform()
    if (project.hasProperty("browser")) {
        systemProperty("browser", project.property("browser"))
    }
    if (project.hasProperty("target")) {
        systemProperty("target", project.property("target"))
    }
    jvmArgs = listOf("-javaagent:${configurations["agent"].singleFile}")
}