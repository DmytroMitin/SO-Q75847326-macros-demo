ThisBuild / scalaVersion := "2.13.10"

lazy val common = project

lazy val core = project
  .dependsOn(macros, common)

lazy val macros = project
  .dependsOn(common)
  .settings(
    libraryDependencies ++= Seq(
      scalaOrganization.value % "scala-reflect" % scalaVersion.value
    ),
  )