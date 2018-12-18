![SonarQube](https://sonarcloud.io/api/project_badges/measure?project=hertzigger_AgaBacbone&metric=alert_status)

A framework plugin for bukkit.

Currently AgaBacbone provides a multithreaded interface to manage console commands, player sessions, time tasks for
players and a Mysql backend that conforms to the lastest UUID model. Storing players ip's, link to those ip with there
UniqueID. links between their UniqueID and players names and session.

Server details and worlds identifiers are stored in the database and linked to player sessions. Currently players
locations are logged against there session in 5 second intervals.

As this framework users Spring framework to inject various classes including the task scheduler, connection pool and
task executor the craftbukkit or spigot jar is required to be unsealed. This can be done by editing the MANIFEST.MF
file within the META-INF folder of the jar and removing the first occurrence of line.

Sealed: true

Otherwise a security violation Exception will be raised when spring tries to inject the classes.
