plugins {
    id("blinq.android.feature")
    id("blinq.android.library.compose")
}

android {
    namespace = "me.blinq.apps.challenge.feature.congrats"
}

dependencies {
    implementation(libs.lottie.compose)
}