plugins {
    java
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.6.10"
}   

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    implementation ("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:3.0.0")
    implementation ("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.0")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.oracle.database.jdbc:ojdbc8")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    implementation ("jakarta.servlet:jakarta.servlet-api") //스프링부트 3.0 이상
    implementation ("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api") //스프링부트 3.0 이상
    implementation ("org.glassfish.web:jakarta.servlet.jsp.jstl") //스프링부트 3.0 이상
    implementation("javax.servlet:javax.servlet-api:3.0.1")
    implementation("org.java-websocket:Java-WebSocket:1.5.4")
    implementation("org.springframework.boot:spring-boot-starter-validation")
}


tasks.withType <Test> {
    useJUnitPlatform()
}

tasks.bootBuildImage {
    builder.set("paketobuildpacks/builder-jammy-base:latest")
}
