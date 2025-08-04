plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.bellmin.ratingbar"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.bundles.compose)
}


afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.jongmin1217"
                artifactId = "ComposeRatingBar"
                version = "1.0.0"

                pom {
                    name.set("ComposeRatingBar")
                    description.set("Compose Rating Bar")
                    url.set("https://github.com/jongmin1217/ComposeRatingBar")
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("jongmin1217")
                            name.set("Jongmin")
                            email.set("syj408886@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:github.com/jongmin1217/ComposeRatingBar.git")
                        developerConnection.set("scm:git:ssh://github.com/jongmin1217/ComposeRatingBar.git")
                        url.set("https://github.com/jongmin1217/ComposeRatingBar")
                    }
                }
            }
        }

        repositories {
            maven {
                name = "jitpack"
                url = uri("https://jitpack.io")
            }
        }
    }
}