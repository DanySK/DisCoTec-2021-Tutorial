import java.awt.GraphicsEnvironment

/*
 * DEFAULT GRADLE BUILD FOR ALCHEMIST SIMULATOR
 */

plugins {
    application
    alias(libs.plugins.kotlin.qa)
    alias(libs.plugins.multiJvmTesting)
    alias(libs.plugins.taskTree)
}

multiJvm {
    jvmVersionForCompilation.set(latestJava)
}

repositories {
    mavenCentral()
}
/*
 * Only required if you plan to use Protelis, remove otherwise
 */
sourceSets {
    main {
        resources {
            srcDir("src/main/protelis")
        }
    }
}

dependencies {
    // The version and modules of Alchemist can be controlled by changing the gradle/libs.versions.toml file
    implementation(libs.bundles.alchemist)
}

// Loaded from gradle.properties
val maxTime: String by project

val alchemistGroup = "Run Alchemist"
/*
 * This task is used to run all experiments in sequence
 */
val runAll by tasks.register<DefaultTask>("runAll") {
    group = alchemistGroup
    description = "Launches all simulations"
}
/*
 * Scan the folder with the simulation files, and create a task for each one of them.
 */
val isInCI = System.getenv("CI") == "true"
File(rootProject.rootDir.path + "/src/main/yaml").listFiles().orEmpty()
    .filter { it.extension == "yml" } // pick all yml files in src/main/yaml
    .sortedBy { it.nameWithoutExtension } // sort them, we like reproducibility
    .forEach {
        // one simulation file -> one gradle task
        val task by tasks.register<JavaExec>("run${it.nameWithoutExtension.capitalize()}") {
            group = alchemistGroup // This is for better organization when running ./gradlew tasks
            description = "Launches simulation ${it.nameWithoutExtension}" // Just documentation
            mainClass.set("it.unibo.alchemist.Alchemist") // The class to launch
            classpath = sourceSets.main.get().runtimeClasspath // The classpath to use
            // Let's use the most recent JVM
            javaLauncher.set(
                javaToolchains.launcherFor { languageVersion.set(JavaLanguageVersion.of(multiJvm.latestJava)) }
            )

            // In case our simulation produces data, we write it in the following folder:
            val exportsDir = File("${projectDir.path}/build/exports/${it.nameWithoutExtension}")
            doFirst {
                // this is not executed upfront, but only when the task is actually launched
                // If the export folder doesn not exist, create it and its parents if needed
                if (!exportsDir.exists()) {
                    exportsDir.mkdirs()
                }
            }
            // These are the program arguments
            args("run", it.absolutePath)
            // checks if the environment is headless
            if (!(GraphicsEnvironment.isHeadless() || isInCI)) {
                // A graphics environment should be available, so load the effects for the UI from the "effects" folder
                // Effects are expected to be named after the simulation file
                args(
                    "--override",
                    """
                    monitors: 
                        type: SwingGUI
                        parameters: 
                            graphics: effects/${it.nameWithoutExtension}.json
                            failOnHeadless: true
                    launcher: 
                        parameters:
                            auto-start: $isInCI
                    """.trimIndent()
                )
            }
            if (isInCI) {
                // If it is running in a Continuous Integration environment,
                // terminate the experiments after a few simulated seconds.
                args("--override", "terminate: { type: AfterTime, parameters: $maxTime }")
            }
            // This tells gradle that this task may modify the content of the export directory
            outputs.dir(exportsDir)
        }
        // task.dependsOn(classpathJar) // Uncomment to switch to jar-based classpath resolution
        runAll.dependsOn(task)
    }
