jobscheduler
========

jobschdeuler written in Java (WIP)

## Development by Eclipse

1. clone this repository
2. [File] - [Import] - [Gradle Project]

## Run application

```
gradle manager:oneJar
java -jar manager/build/libs/manager-{version}-standalone.jar server manager/setting/configuration.yml
```

try to access ``http://localhost:18080/node``
