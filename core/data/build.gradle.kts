plugins {
    id("blinq.android.library")
    id("blinq.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "me.blinq.apps.challenge.core.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:datastore"))
    implementation(project(":core:network"))

    testImplementation(project(":core:testing"))
    //testImplementation(project(":core:datastore-test"))

    implementation(libs.androidx.core.ktx)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
}