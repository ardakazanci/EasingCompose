plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.ardakazanci.easing.compose"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(project(":easing-core"))
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.animation.core)
}
