import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.MavenBuildStep
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.finishBuildTrigger
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2019.1"

project {

    buildType(ServiceOmega)
    buildType(ServiceBeta)
    buildType(ServiceAlpha)
    buildType(SharedModel)
}

object ServiceAlpha : BuildType({
    name = "Service Alpha"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        maven {
            name = "Run Tests"
            goals = "clean test"
            pomLocation = "service-alpha/pom.xml"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_11_x64%"
            coverageEngine = idea {
                includeClasses = "ca.purpleowl.*"
            }
        }
        maven {
            name = "Build Service"
            goals = "install"
            pomLocation = "service-alpha/pom.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_11_x64%"
        }
    }

    triggers {
        vcs {
        }
        finishBuildTrigger {
            buildType = "${SharedModel.id}"
        }
    }

    dependencies {
        snapshot(SharedModel) {
        }
    }
})

object ServiceBeta : BuildType({
    name = "Service Beta"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        maven {
            name = "Run Tests"
            goals = "clean test"
            pomLocation = "service-beta/pom.xml"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_11_x64%"
            coverageEngine = idea {
                includeClasses = "ca.purpleowl.*"
            }
        }
        maven {
            name = "Build Service"
            goals = "install"
            pomLocation = "service-beta/pom.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_11_x64%"
        }
    }

    triggers {
        vcs {
        }
        finishBuildTrigger {
            buildType = "${SharedModel.id}"
        }
    }

    dependencies {
        snapshot(SharedModel) {
        }
    }
})

object ServiceOmega : BuildType({
    name = "Service Omega"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        maven {
            name = "Run Tests"
            goals = "clean test"
            pomLocation = "service-omega/pom.xml"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_11_x64%"
            coverageEngine = idea {
                includeClasses = "ca.purpleowl.*"
            }
        }
        maven {
            name = "Build Service"
            goals = "install"
            pomLocation = "service-omega/pom.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_11_x64%"
        }
    }

    triggers {
        vcs {
        }
        finishBuildTrigger {
            buildType = "${SharedModel.id}"
        }
    }

    dependencies {
        snapshot(SharedModel) {
        }
    }
})

object SharedModel : BuildType({
    name = "Shared Model"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        maven {
            name = "Run Shared Model Tests"
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_11_x64%"
            coverageEngine = idea {
                includeClasses = "ca.purpleowl.*"
            }
        }
        maven {
            name = "Build Shared Model"
            goals = "install"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_11_x64%"
        }
    }

    triggers {
        vcs {
        }
    }
})
