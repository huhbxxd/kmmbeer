import kmmbeer.Deps
import kmmbeer.Configuration

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("kotlin-parcelize")
    id("app.cash.sqldelight")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Compose
                with(compose) {
                    api(runtime)
                    api(foundation)
                    api(material)
                    api(material3)
                    api(materialIconsExtended)
                }

                // Ktor
                with(Deps.Io.Ktor) {
                    api(ktorClientCore)
                    api(ktorSerializationKotlinxJson)
                    api(ktorClientContentNegotiation)
                    api(ktorClientLogging)
                }

                // Logback for ktor logging
                implementation(Deps.Logback.logbackClassic)

                // SqlDelight
                with(Deps.CashApp.SQLDelight) {
                    api(coroutinesExtensions)
                    api(primitiveAdapters)
                }

                // Koin
                with(Deps.Koin) {
                    api(core)
                    api(test)
                }

                // KotlinX Serialization Json
                implementation(Deps.Org.JetBrains.Kotlinx.kotlinxSerializationJson)

                // Coroutines
                implementation(Deps.Org.JetBrains.Kotlinx.coroutinesCore)

                // MVIKotlin
                with(Deps.ArkIvanov.MVIKotlin) {
                    api(mvikotlin)
                    api(mvikotlinMain)
                    api(mvikotlinExtensionsCoroutines)
                }

                // Decompose
                with(Deps.ArkIvanov.Decompose) {
                    api(decompose)
                    api(extensionsCompose)
                }

                // Image Loading
                api(Deps.Github.imageLoader)
                implementation(Deps.ArkIvanov.Essenty.lifecycle)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                // Ktor
                implementation(Deps.Io.Ktor.ktorClientAndroid)

                // SqlDelight
                implementation(Deps.CashApp.SQLDelight.androidDriver)

                // Koin
                implementation(Deps.Koin.android)
            }
        }

        val iosMain by getting {
            dependsOn(commonMain)

            dependencies {
                // Ktor
                implementation(Deps.Io.Ktor.ktorClientDarwin)

                // SqlDelight
                implementation(Deps.CashApp.SQLDelight.nativeDriver)
            }
        }

        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
    }
}

android {
    namespace = "com.lmd.kmmbeer"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}