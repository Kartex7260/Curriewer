plugins {
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.kapt)
	alias(libs.plugins.hilt)
}

android {
	namespace = "kanti.currency"
	compileSdk = 34

	defaultConfig {
		minSdk = 29

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
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
	composeOptions {
		kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
	}
}

dependencies {

	implementation(platform(libs.compose.bom))
	implementation(libs.compose.ui.m3)
	implementation(libs.compose.ui.tooling.preview)
	debugImplementation(libs.compose.ui.tooling)
	implementation(libs.haze)
	implementation(libs.compose.navigation)

	implementation(project(":data:app"))
	implementation(project(":data:currency"))
	implementation(project(":domain:currencies:manipulation"))
	implementation(project(":shared"))
	implementation(project(":ui:common"))

	implementation(libs.hilt.android)
	kapt(libs.hilt.compiler)
	implementation(libs.hilt.compose)

	implementation(libs.kotlinx.datetime)
}