plugins {
    java
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "be.kdg"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-security")
    implementation ("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    implementation("org.webjars:bootstrap:5.3.3")
    runtimeOnly ("org.postgresql:postgresql:42.7.5")
    testRuntimeOnly ("org.junit.platform:junit-platform-launcher")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.webjars:webjars-locator-core:0.48")
    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("org.springframework.security:spring-security-test")
    testImplementation ("com.fasterxml.jackson.core:jackson-databind")
    testImplementation ("com.h2database:h2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
