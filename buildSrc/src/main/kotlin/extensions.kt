import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependencyNotation: List<Any>) {
    for (dep in dependencyNotation) {
        add("implementation", dep)
    }
}

fun DependencyHandler.api(dependencyNotation: List<Any>) {
    for (dep in dependencyNotation) {
        add("api", dep)
    }
}

fun DependencyHandler.kapt(dependencyNotation: List<Any>) {
    for (dep in dependencyNotation) {
        add("kapt", dep)
    }
}
fun DependencyHandler.debugImplementation(dependencyNotation: List<Any>) {
    for (dep in dependencyNotation) {
        add("debugImplementation", dep)
    }
}

fun DependencyHandler.androidTestImplementation(dependencyNotation: List<Any>) {
    for (dep in dependencyNotation) {
        add("androidTestImplementation", dep)
    }
}

fun DependencyHandler.testImplementation(dependencyNotation: List<Any>) {
    for (dep in dependencyNotation) {
        add("testImplementation", dep)
    }
}