import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform

name := """game-arena"""
organization := "com.wlangiewicz"
version := "1.0-SNAPSHOT"
scalaVersion := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature", "-target:jvm-1.8")


libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "com.github.tototoshi" %% "play-json-naming" % "1.1.0",
  "ai.x" %% "play-json-extensions" % "0.8.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

SbtScalariform.scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(SpacesAroundMultiImports, false)
  .setPreference(CompactControlReadability, false)