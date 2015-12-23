name := "play-ws-test"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.google.code.gson" % "gson" % "2.2.4"
)     

play.Project.playJavaSettings

unmanagedSourceDirectories in Compile += baseDirectory.value / "src/java/model"

unmanagedSourceDirectories in Compile += baseDirectory.value / "src/java/requestOperations"

unmanagedSourceDirectories in Compile += baseDirectory.value / "src/java/notificationCenter"

