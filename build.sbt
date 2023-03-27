ThisBuild / scalaVersion := "2.13.10"

lazy val core = project
  .dependsOn(macros)

lazy val macros = project
  .settings(
    libraryDependencies ++= Seq(
      scalaOrganization.value % "scala-reflect" % scalaVersion.value
    ),
  )