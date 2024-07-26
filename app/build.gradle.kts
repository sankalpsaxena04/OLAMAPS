plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    id("kotlin-kapt")
    id("kotlin-parcelize")

}

android {
    namespace = "com.sandeveloper.olamaps"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sandeveloper.olamaps"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.retrofit)
    implementation(libs.moshi.converter)
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.core)
    implementation(libs.moe.android.sdk)
    implementation(libs.android.sdk)
    implementation(libs.android.sdk.directions.models)
    implementation(libs.android.sdk.services)
    implementation(libs.android.sdk.turf)
    implementation(libs.android.plugin.markerview.v9)
    implementation(libs.android.plugin.annotation.v9)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.lifecycle.extensions)
    kapt(libs.lifecycle.compiler)
    implementation(files("libs/maps-1.0.68.aar"))
    implementation (libs.okhttpprofiler)
}