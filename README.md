# JAASTwoFactorAuthentcation

This is a simple demonstration of JAAS done by implementing a two factor authentication system. In this example we prompt the user for a username and a password. And then an SMS verification challenge.

The SMS verification is simulated by sending the code to a TCP socket (8000) so use a program like net cat to listen on port 8000 when you are testing this code.

    nc -l -k 8000

This application uses the default JAAS Login configuration option. After compilation, the following JVM parameter should be passed to point to the correct config file.

    -Djava.security.auth.login.conf=jaas.config

On IDE's like JetBrains IntelliJ you can add this code to the Run/Debug configuration's VM Options value.


