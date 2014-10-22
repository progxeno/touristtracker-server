name := "todoJava"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(javaJdbc, javaEbean,
  "net.vz.mongodb.jackson" %% "play-mongo-jackson-mapper" % "1.1.0",
  "com.google.code.gson" % "gson" % "2.2"
)

javaOptions in Test += "-Dconfig.file=conf/test.conf"

play.Project.playJavaSettings
