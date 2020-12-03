import io.kotless.plugin.gradle.dsl.KotlessConfig.Optimization.Autowarm
import io.kotless.plugin.gradle.dsl.kotless
import io.kotless.resource.Lambda.Config.Runtime
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

group = "example"
version = "0.1.0"

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
    id("tanvd.kosogor") version "1.0.9"
    id("io.kotless") version "0.1.7-beta-4"
}

repositories {
    jcenter()
}

dependencies {
    implementation("io.kotless", "ktor-lang", "0.1.7-beta-4")
}

tasks.withType<KotlinJvmCompile> {
    kotlinOptions {
        jvmTarget = "11"
        languageVersion = "1.3"
        apiVersion = "1.3"
    }
}

kotless {
    config {
        bucket = "test-kotless"
        prefix = "minimal-example"

        dsl {
            type = io.kotless.DSLType.Ktor
        }
        terraform {
            profile = "bet_tds_dev"
            region = "eu-west-1"
        }
        optimization {
            autowarm = Autowarm(enable = false, minutes = -1)
        }
    }

    webapp {
        lambda {
            runtime = Runtime.GraalVM
        }
    }
}