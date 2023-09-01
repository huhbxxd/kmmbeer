import kmmbeer.Configuration
import kmmbeer.Deps
import kmmbeer.Versions

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.lmd.kmmbeer.android"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        applicationId = "com.lmd.kmmbeer.android"
        minSdk = Configuration.minSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.activity:activity-compose:1.7.2")

    // Koin
    with(Deps.Koin) {
        api(android)
    }
}