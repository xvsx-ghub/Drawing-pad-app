plugins {
    alias(drawingPadLibs.plugins.kotlinMultiplatform)
    alias(drawingPadLibs.plugins.androidKotlinMultiplatformLibrary)
    alias(drawingPadLibs.plugins.androidLint)
    alias(drawingPadLibs.plugins.composeMultiplatform)
    alias(drawingPadLibs.plugins.composeCompiler)
}

kotlin {
    sourceSets.all {
        languageSettings.optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
    }

    androidLibrary {
        namespace = "com.wiswm.nav.drawing_pad"
        compileSdk = 36
        minSdk = 24

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    val xcfName = "drawing-padKit"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(drawingPadLibs.kotlin.stdlib)
                // Add KMP dependencies here
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(drawingPadLibs.androidx.lifecycle.viewmodelCompose)
                implementation(drawingPadLibs.androidx.lifecycle.runtimeCompose)
            }
        }

        commonTest {
            dependencies {
                implementation(drawingPadLibs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                implementation(compose.preview)
                implementation(drawingPadLibs.androidx.activity.compose)
            }
        }

        getByName("androidDeviceTest") {
            dependencies {
                implementation(drawingPadLibs.androidx.runner)
                implementation(drawingPadLibs.androidx.core)
                implementation(drawingPadLibs.androidx.testExt.junit)
            }
        }

        iosMain {
            dependencies {
            }
        }
    }
}