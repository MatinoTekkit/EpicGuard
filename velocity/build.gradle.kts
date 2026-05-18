plugins {
    alias(libs.plugins.runvelocity)
}

dependencies {
    implementation(projects.core)

    implementation(libs.cloud.velocity)
    implementation(libs.commons.compress)
    implementation(libs.fuzzywuzzy)
    implementation(libs.geoip)
    implementation(libs.hikaricp)
    implementation(libs.libby.velocity)

    compileOnly(libs.velocity)
    annotationProcessor(libs.velocity)
}

tasks {
    shadowJar {
        exclude("org/slf4j/**")
        relocate("net.byteflux.libby", "me.xneox.epicguard.velocity.libby")
        relocate("com.zaxxer.hikari", "me.xneox.epicguard.libs.com.zaxxer.hikari")
        relocate("com.mysql.cj", "me.xneox.epicguard.libs.mysql")
        relocate("org.xerial", "me.xneox.epicguard.libs.org.xerial")
        relocate("org.apache.commons", "me.xneox.epicguard.libs.commons")
        relocate("me.xdrop.fuzzywuzzy", "me.xneox.epicguard.libs.fuzzywuzzy")
        relocate("cloud.commandframework", "me.xneox.epicguard.libs.cloud")
        relocate("io.leangen.geantyref", "me.xneox.epicguard.libs.geantyref")
        relocate("com.fasterxml.jackson", "me.xneox.epicguard.libs.jackson")
        relocate("com.maxmind", "me.xneox.epicguard.libs.maxmind")
    }
    build {
        dependsOn(shadowJar)
    }
    runVelocity {
        velocityVersion(libs.versions.velocity.get())
    }
}
