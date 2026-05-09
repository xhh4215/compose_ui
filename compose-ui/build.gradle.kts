import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("maven-publish")

}


val gId = "com.xingchidongli"

val ver = "0.1.1"

val aId = "compose-ui"

publishing {
    publications {
        afterEvaluate {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = gId
                artifactId = aId
                version = ver
            }
        }
    }

    repositories {
        maven {
            url = uri("https://packages.aliyun.com/68199f0ce5b44f78b1c0012f/maven/2536684-release-inozy0")
            credentials {
                username = project.findProperty("aliyun.xcdl.username") as String?
                    ?: System.getenv("USERNAME")
                password = project.findProperty("aliyun.xcdl.token") as String? ?: System.getenv(
                    "TOKEN"
                )
            }
        }
    }
}
android {
    namespace = "com.xingchidongli.robot.compose_ui"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.lifecycle.lifecycle.viewmodel.compose3)
    implementation(libs.androidx.compose.constraintlayout)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.kotlin.compose.coil)
    implementation(libs.kotlin.compose.coil.gif)
    implementation(libs.com.github.glide)
    implementation(libs.com.github.glide.ksp)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}