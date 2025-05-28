plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.hidirektor.lingo44"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.hidirektor.lingo44"
        minSdk = 29
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.ccp)
    implementation(libs.chaosleung.pinview)
    implementation(libs.appcompat.v110)
    implementation(libs.superior.toasts)
    implementation(libs.gson)
    implementation(libs.avvylib)
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}