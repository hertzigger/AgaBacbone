SELECT
  username.username,
  server.name,
  world.name
FROM AgaBacbone.username
  JOIN AgaBacbone.usernameHasSession
    ON AgaBacbone.username.idUsername = AgaBacbone.usernameHasSession.idUsername
  JOIN AgaBacbone.session
    ON AgaBacbone.session.idSession = AgaBacbone.usernameHasSession.idSession
  JOIN AgaBacbone.world
    ON AgaBacbone.session.idWorld = AgaBacbone.world.idWorld
  JOIN AgaBacbone.server
    ON AgaBacbone.world.idServer = AgaBacbone.server.idServer
  WHERE session.logout IS NULL;