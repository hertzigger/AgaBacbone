SELECT
  username.username,
  session.login,
  IF(session.logout IS NOT NULL, session.logout, NOW()) AS Logout,
  (SELECT
  TIMESTAMPDIFF(MINUTE, session.login, IF(session.logout IS NOT NULL, session.logout, NOW()))) AS LoginTime
FROM AgaBacbone.username
  JOIN AgaBacbone.usernameHasSession
    ON AgaBacbone.username.idUsername = AgaBacbone.usernameHasSession.idUsername
  JOIN AgaBacbone.session ON AgaBacbone.session.idSession = AgaBacbone.usernameHasSession.idSession