plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    setCompileSdkVersion(Configs.compileSdkVersion)
    defaultConfig {
        setMinSdkVersion(Configs.minSdkVersion)
        setTargetSdkVersion(Configs.targetSdkVersion.toString())
        buildConfigField("int", "DB_VERSION", "1")
        buildConfigField("String", "REST_URL", "\"https://crudcrud.com/\"")
        buildConfigField("String", "API_KEY", "\"space_to_key\"")
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(ProjectRootLibraries.domain))
    implementation(project(ProjectRootLibraries.support))
    requiredLibraries()
    supportLibraries()
    roomLibraries()
    dateTimeLibraries()
    networkLibraries()
    firebaseLibraries()
    dataStoreLibraries()
    implementation(GoogleMiscLibraries.playservices_auth)
    implementation(GoogleMiscLibraries.google_sheets){
        exclude("org.apache.httpcomponents")
    }
    implementation(GoogleMiscLibraries.google_oauth_jetty)
    implementation(GoogleMiscLibraries.google_api_client) {
        exclude("org.apache.httpcomponents")
    }
}