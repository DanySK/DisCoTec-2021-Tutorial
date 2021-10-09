[![DOI](https://zenodo.org/badge/359458176.svg)](https://zenodo.org/badge/latestdoi/359458176)
[![CI](https://github.com/DanySK/DisCoTec-2021-Tutorial/actions/workflows/build-and-deploy.yml/badge.svg)](https://github.com/DanySK/DisCoTec-2021-Tutorial/actions/workflows/build-and-deploy.yml)

# Alchemist Tutorial presented at [DisCoTec 2021](https://www.discotec.org/2021/)

This repository contains all examples presented at the Alchemist tutorial 
held at [DisCoTec 2021](https://www.discotec.org/2021/).

## Prerequisites

This project requires a Java version capable of executing Gradle.
If Gradle can run, the project is pre-configured to download and execute on the latest supported java version.
If you have no Java installed, we recommend installing the latest LTS Java from [AdoptOpenJDK](https://adoptopenjdk.net/)
(unless a new LTS is out and Gradle does not yet support it).

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

Press <kbd>P</kbd> to start the simulation.
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
