# QServer
QuantumServer (QServer) is a HTTP server coded using java by @qu-cipher

# Source Code version: 0.0.5-beta

## Usage

* 1. Initializing

```java
import qu.cipherr.QServer.Router;
import qu.cipherr.QServer.Objects.Route;

public class MyAwesomeApp {
    public static void main(String[] args){
        Router s = new Router(9999, true, new Route("/", new Asghar()));
        s.start();
    }
}

```

