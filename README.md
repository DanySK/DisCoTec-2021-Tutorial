# Alchemist Tutorial presented at [DisCoTec 2021](https://www.discotec.org/2021/)

This repository contains all examples presented at the Alchemist tutorial 
held at [DisCoTec 2021](https://www.discotec.org/2021/).

## Prerequisites

This project requires Java 11 or above correctly installed. It has been tested with Java 11 and Java 15,
it is likely to work with newer versions too.
If you have no Java installed, we recommend to install Java 11 from [AdoptOpenJDK](https://adoptopenjdk.net/)

## Instructions

This project showcases the [Alchemist](https://github.com/AlchemistSimulator/Alchemist) simulator,
and does so by leveraging [Gradle](https://gradle.org) to resolve dependencies and execute the simulator.
To this end, it comes with a pre-configured gradle build.

### Importing the repository
The preferred way to import this repository is via the [Git SCM](https://git-scm.com/).
``
git clone https://github.com/DanySK/DisCoTec-2021-Tutorial.git
cd DisCoTec-2021-Tutorial
``

Alternatively,
the repository can be [downloaded as compressed archive](https://github.com/DanySK/DisCoTec-2021-Tutorial/archive/refs/heads/master.zip).
The archive should then get upacked, and a terminal should be prepared pointing to the directory containing the `build.gradle.kts` file.

### Execution

Gradle can be launched by relying on the provided wrapper script.
On Unix (Linux, MacOS X, WSL, etc.) or on Bash emulators (Git Bash, Cygwin 64):
``
./gradlew <taskName>
``
On Windows' cmd.exe or PowerShell:
``
gradlew.bat <taskName>
``
In the remainder of this guide, we will use the Unix syntax.

#### Displaying available tasks

All the available tasks can be displayed by executing `./gradlew tasks`.
A task category named `Run Alchemist tasks` will contain all tasks that launch Alchemist simulations.

#### Executing simulations

Tasks are named after the corresponding YAML file,
with the pattern `run<name-of-simulation-file>`.
Gradle supports shortened names: launching `./gradlew run00` is the same as `./gradlew run00-deployment-in-three-points`,
unless there is some ambiguity (e.g., a new file whose name starts in `00` has been added to the simulation files folder).

One task named `runAll` has been created to launch all simulations one by one.

Note that the first launch will be slow, since Gradle will download all the required files.
They will get cached in the user's home folder (as per Gradle normal behavior)
and thus subsequent execution will be much faster.

#### Using the graphical interface

Press <kb>P</kb> to start the simulation.
The available UI bindings are the following:

| Key binding             | Active         | Effect                                                                |
| ------------------------| -------------- | --------------------------------------------------------------------- |
| <kbd>L</kbd>            | always         | (En/Dis)ables the painting of links between nodes                     |
| <kbd>M</kbd>            | always         | (En/Dis)ables the painting of a marker on the closest node            |
| <kbd>Mouse pan</kbd>    | in normal mode | Moves around                                                          |
| <kbd>Mouse wheel</kbd>  | in normal mode | Zooms in/out                                                          |
| <kbd>Double click</kbd> | in normal mode | Opens a frame with the closest node information                       |
| <kbd>Right click</kbd>  | in normal mode | Enters screen rotation mode                                           |
| <kbd>P</kbd>            | always         | Plays/pauses the simulation                                           |
| <kbd>R</kbd>            | always         | Enables the real-time mode                                            |
| <kbd>Left arrow</kbd>   | always         | Speeds the simulation down (more calls to the graphics)               |
| <kbd>Right arrow</kbd>  | always         | Speeds the simulation up (less calls to the graphics)                 |
| <kbd>S</kbd>            | always         | Enters / exits the select mode (nodes can be selected with the mouse) |
| <kbd>O</kbd>            | in select mode | Selected nodes can be moved by drag and drop                          |
| <kbd>E</kbd>            | in select mode | Enters edit mode (to manually change node contents)                   |
