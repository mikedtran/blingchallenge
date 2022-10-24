plugins {
    id("blinq.android.library")
    id("blinq.android.hilt")
}

android {
    namespace = "me.blinq.apps.challenge.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(project(":core:testing"))
}