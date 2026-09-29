import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
}

android {
    // Provisional namespace — requires confirmation before considered stable.
    // See: docs/ARCHITECTURE.md, spec KMP-001 §13.
    namespace = "uy.eliasworks.miecosystem.android"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        // Provisional applicationId — requires confirmation before considered stable.
        applicationId = "uy.eliasworks.miecosystem"
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
        versionCode = 1
        versionName = "0.0.1"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
}
