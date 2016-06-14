organization  := "tim.sielemann"

version       := "0.1"

scalaVersion  := "2.11.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
scalacOptions in (Compile,doc) := Seq("-groups", "-implicits")
autoAPIMappings := true
