plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.sami.tradingchallengetracker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.salim.tradingchallengetracker"
        minSdk = 24
        targetSdk = 34
        versionCode = 15
        versionName = "1.3.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("samiUniversal") {
            val keyPath = file("${project.projectDir}/sami-signing-key.p12")
            val keystoreFile = if (keyPath.exists()) keyPath else file("sami-signing-key.p12")
            if (keystoreFile.exists()) {
                storeFile = keystoreFile
                storeType = "PKCS12"
                storePassword = "samitrading"
                keyAlias = "sami"
                keyPassword = "samitrading"
                enableV1Signing = true
                enableV2Signing = true
                enableV3Signing = true
            }
        }
    }

    buildTypes {
        debug {
            val keyPath = file("${project.projectDir}/sami-signing-key.p12")
            val keystoreFile = if (keyPath.exists()) keyPath else file("sami-signing-key.p12")
            if (keystoreFile.exists()) {
                signingConfig = signingConfigs.getByName("samiUniversal")
            }
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            val keyPath = file("${project.projectDir}/sami-signing-key.p12")
            val keystoreFile = if (keyPath.exists()) keyPath else file("sami-signing-key.p12")
            if (keystoreFile.exists()) {
                signingConfig = signingConfigs.getByName("samiUniversal")
            } else {
                signingConfig = signingConfigs.getByName("debug")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/LICENSE*"
            excludes += "META-INF/NOTICE*"
            excludes += "META-INF/*.version"
            excludes += "META-INF/INDEX.LIST"
            excludes += "META-INF/io.netty.versions.properties"
            excludes += "**/*.proto"
        }
    }
}

tasks.configureEach {
    if (name.contains("ArtProfile")) {
        enabled = false
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-ktx:1.9.1")
    implementation("androidx.webkit:webkit:1.11.0")

    // Firebase Cloud Messaging for native push notifications
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation("com.google.firebase:firebase-messaging-ktx")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
}
