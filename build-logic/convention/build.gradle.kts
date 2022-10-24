plugins {
    `kotlin-dsl`
}

group = "me.blinq.challenge.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "blinq.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "blinq.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "blinq.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "blinq.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "blinq.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
//        register("androidTest") {
//            id = "blinq.android.test"
//            implementationClass = "AndroidTestConventionPlugin"
//        }
        register("androidHilt") {
            id = "blinq.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
    }
}
