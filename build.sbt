lazy val settings = Seq(
  scalaVersion := "2.12.4",
  name := "ExcelBloqueado",
  organization := "arena.com",
  version := "1.0",
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-Xcheckinit", "-encoding", "utf8", "-feature"),
  fork := true
)
lazy val officeLoc = "https://github.com/Rhodolfo/ScalaOffice.git#%s".format("v2.4")
lazy val officePrj = RootProject(uri(officeLoc))
lazy val excelBloqueado = (project in file("."))
  .settings(settings: _*)
  .dependsOn(officePrj)
