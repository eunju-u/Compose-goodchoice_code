plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
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

    implementation ("androidx.core:core-ktx:1.13.1")
    implementation ("androidx.appcompat:appcompat:1.7.0")
    implementation ("com.google.android.material:material:1.12.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.2.1")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.6.1")

    // Compose
    implementation ("androidx.compose.material3:material3:1.2.1")
    implementation ("androidx.compose.material:material:1.6.8")
    implementation ("androidx.compose.foundation:foundation:1.6.8")
    implementation ("androidx.compose.ui:ui:1.6.8")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.6.8")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.6.8")
    implementation ("androidx.activity:activity-compose:1.9.1")

    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    //image load library
    implementation ("io.coil-kt:coil-compose:2.4.0")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.8.4")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")

    implementation ("com.google.accompanist:accompanist-swiperefresh:0.26.5-rc")

    //compose 용 웹뷰
    implementation ("com.google.accompanist:accompanist-webview:0.30.0")

    //room db
    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")

    //Hilt
    implementation ("com.google.dagger:hilt-android:2.49")
    kapt ("com.google.dagger:hilt-compiler:2.48.1")

    // 네이버 지도 SDK
    implementation("com.naver.maps:map-sdk:3.18.0")
    implementation("io.github.fornewid:naver-map-compose:1.7.2")
    implementation("com.google.android.gms:play-services-location:21.3.0")

    //모듈화
    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(project(":database"))
    implementation(project(":data"))
    implementation(project(":ui"))
}