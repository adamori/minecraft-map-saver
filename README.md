# MapSaver PaperMC/Spigot Minecraft Server Plugin

The Minecraft Server-side plugin adds a `savemap` command to export all blocks into a CSV file based on a specified range of locations.

*Created on [PaperMC/Spigot Minecraft Server Plugin Template](https://github.com/CrimsonWarpedcraft/plugin-template)*


[![Build, Test, and Release](https://github.com/markarenin/minecraft-map-saver/actions/workflows/main.yml/badge.svg)](https://github.com/markarenin/minecraft-map-saver/actions/workflows/main.yml)


## Installing

Insert the .jar file into the plugins folder on your Minecraft server.


## Config Files 📁
* Sample plugin.yml with autofill name, version, and main class.
* Empty config.yml
* Gradle build config
* Simple .gitignore for common Gradle files


### Release Info
Stable versions of this repo are tagged `vX.Y.Z` and have an associated [release](https://github.com/CrimsonWarpedcraft/plugin-template/releases).

Testing versions of this repo are tagged `vX.Y.Z-RC-N` and have an associated [pre-release](https://github.com/CrimsonWarpedcraft/plugin-template/releases).

Development versions of this repo are pushed to the master branch and are **not** tagged.

#### Release and Versioning Strategy
| Event             | Version Format       | CI Action                        | GitHub Release Draft? |
|-------------------|----------------------|----------------------------------|-----------------------|
| PR                | yyMMdd-HHmm-SNAPSHOT | Build and test                   | No                    |
| Schedule          | yyMMdd-HHmm-SNAPSHOT | Build, test, and notify          | No                    |
| Push to `main`    | 0.0.0-SNAPSHOT       | Build, test, release, and notify | No                    |
| Tag `vX.Y.Z-RC-N` | X.Y.Z-SNAPSHOT       | Build, test, release, and notify | Pre-release           |
| Tag `vX.Y.Z`      | X.Y.Z                | Build, test, release, and notify | Release               |


## Building locally
Thanks to [Gradle](https://gradle.org/), building locally is easy no matter what platform you're on. Simply run the following command:

```text
./gradlew build
```

This build step will also run all checks and tests, making sure your code is clean.

JARs can be found in `build/libs/`.

## Contributing
See [CONTRIBUTING.md](https://github.com/CrimsonWarpedcraft/plugin-template/blob/main/CONTRIBUTING.md).

---

I think that's all... phew! Oh, and update this README! ;)
