plugins {
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
	namespace = "kanti.curriewer.data.retrofit"
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
	buildTypes.forEach { buildType ->
		val currencyAPI: String by project
		buildType.buildConfigField(type = "String", name = "CurrencyAPI", value = currencyAPI)
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		buildConfig = true
	}
}

dependencies {

	implementation(libs.hilt.android)
	implementation(libs.retrofit)
}