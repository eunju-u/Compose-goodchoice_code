plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.goodchoice"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.goodchoice"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    // Compose
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui)

    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.hilt.navigation.compose)

    //image load library
    implementation(libs.coil)

    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewmodelCompose)
    implementation(libs.androidx.lifecycle.viewmodelKtx)

    implementation(libs.google.accompanist.swiperefresh)

    //compose 용 웹뷰
    implementation(libs.com.google.accompanist.webview)

    //room db
    implementation(libs.room.runtime)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // 네이버 지도 SDK
    implementation(libs.naver.map)
    implementation(libs.naver.map.compose)
    implementation(libs.google.play.services.location)

    //모듈화
    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(project(":database"))
    implementation(project(":data"))
    implementation(project(":ui"))
}