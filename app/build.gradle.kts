plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.serialization)
}

android {
    namespace = "com.example.githubclientapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.githubclientapp"
        minSdk = 24
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
        compose = true
    }
    lint {
        sarifReport = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.compose.material.iconsExtended)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)

    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.gson)

    detektPlugins(libs.detekt.formatting)
    detektPlugins(libs.detekt.rules)
    detektPlugins(libs.detekt.rules.twitter)

    implementation(libs.kotlinx.collections.immutable)

    implementation(libs.coil)

    // メモリリーク検出ライブラリ
    debugImplementation(libs.leakcanary)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
detekt {
    parallel = true
    config.setFrom("${rootProject.projectDir}/config/detekt/detekt.yml")
    buildUponDefaultConfig = true
    autoCorrect = true // 自動でフォーマット
    basePath = rootDir.absolutePath
    reportsDir = file("${rootProject.projectDir}/build/reports/detekt.xml")
}