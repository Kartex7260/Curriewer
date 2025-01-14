plugins {
	id("java-library")
	alias(libs.plugins.jetbrainsKotlinJvm)
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

	implementation(libs.dagger)
	implementation(libs.coroutines.core)
}