import java.util.Properties

val properties = Properties().apply {
    load(project.rootProject.file("local.properties").inputStream())
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.river.pokedex"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.river.pokedex"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"${properties["base.url"]}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    // Test
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.bundles.test)

    // Debug
    debugImplementation(libs.bundles.debug)

    // Androidx
    implementation(libs.bundles.androidx)
    implementation(platform(libs.compose.bom))
    ksp(libs.room.compiler)

    // Coroutine
    implementation(libs.bundles.coroutines)

    // DI
    implementation(libs.bundles.hilt)
    ksp(libs.hilt.compiler)

    // Third-Party
    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.retrofit)
    implementation(libs.kotlinx.serialization.json)

    // ETC
    implementation(libs.timber)
    implementation(libs.lottie.compose)
    implementation(libs.coil)
    implementation(libs.compose.shimmer)
}
