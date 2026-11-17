# Section 07 - Docker

Each Spring Boot project has a Dockerfile that packages its executable JAR.
Build the JARs first with `.\mvnw clean package -DskipTests` from each project
directory, then build an image with `docker build -t <service> .`.
