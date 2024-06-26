plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.pokemon_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pokemon_app"
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.4.0")
    implementation("androidx.lifecycle:lifecycle-livedata:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.4.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.4.0")

    // Network
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // RxJava + RxAndroid + RxJavaAdapter Retrofit
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation("io.reactivex.rxjava3:rxjava:3.0.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")

    // Dagger 2
    implementation("com.google.dagger:dagger:2.35.1")
    implementation("com.google.dagger:dagger-android:2.35.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    annotationProcessor("com.google.dagger:dagger-compiler:2.35.1")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    // ProgressView
    implementation("com.github.skydoves:progressview:1.1.2")

    // Room Database
    implementation("androidx.room:room-runtime:2.4.0")
    annotationProcessor("androidx.room:room-compiler:2.4.0")
    androidTestImplementation("androidx.room:room-testing:2.4.0")
}

