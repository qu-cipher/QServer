
# QServer

QServer or QuantumServer is a HTTP server & handler based on `Router` and `Route`


## Usage

For this, We will create a [Router](https://github.com/qu-cipher/QServer/blob/main/src/main/java/qu/cipherr/QServer/Router.java) object.
As a parameter, we will pass in a port number (1 - 65535), debug mode activation (true | false) and a [Route](https://github.com/qu-cipher/QServer/tree/main/src/main/java/qu/cipherr/QServer/Objects#route) object.

```java
import qu.cipherr.QServer.Router;
import qu.cipherr.QServer.Objects.Route;

public class MyAwesomeApp {
    public static void main(String[] args){
        Router s = new Router(9999, true, new Route("/", new MyRouteHandler()));
        s.start();
    }
}
```
You can create handlers separate classes:
```java
package my.pack;

import qu.cipherr.QServer.Objects.Route;
import my.pack.MyHandler;

public class MyAwesomeApp{
    public static void main(String[] args){
        MyHandler h = new MyHandler(); // The handler class
        Route route = new Route("/path", h); // The route class

        Router s = new Router(1025, true, new Route("/", new MyOtherHandler()), route);
        s.start();
    }
}
```

 


## Authors

- [@qu-cipher](https://www.github.com/qu-cipher)


## Licenses
* [![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)




## Support

- Telegram: [@QuantumCipherr](https://t.me/quantumcipherr)
- E-Mail: qu.cipherr@yahoo.com
