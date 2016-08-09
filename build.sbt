name := "address-book"

organization := "com.gumtree"

version := "1.0"

scalaVersion := "2.11.8"

val compileDependencies = Seq (
  "com.github.tototoshi" %% "scala-csv" % "1.3.3",
  "joda-time"            % "joda-time"  % "2.9.4"
)

val testDependencies = Seq (
  "org.scalatest" %% "scalatest"    % "3.0.0"          % "test",
  "org.mockito"   %  "mockito-core" % "2.0.99-beta"    % "test"
)

libraryDependencies ++= compileDependencies ++ testDependencies