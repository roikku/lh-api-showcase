Lufthansa Open API Showcase  [![Build Status](https://roikku.ci.cloudbees.com/buildStatus/icon?job=lh-api-showcase)](https://roikku.ci.cloudbees.com/job/lh-api-showcase/)
=================

This simple web app provides a showcase for the [Lufthansa Open API](https://developer.lufthansa.com). The API and data are used in conformance with the [license](https://developer.lufthansa.com/General_Terms_and_Conditions).

## Live Demo

This app is online at [lh-api-showcase.appspot.com](http://lh-api-showcase.appspot.com).

##Some Technical Aspects

This app is written in Java and is developped using the [GWT](http://www.gwtproject.org) framework. It reposes on the MVP architecture (Model--View--Presenter) as described in the article [Building MVP apps](http://www.gwtproject.org/articles/mvp-architecture.html).

This sample project provides:
- sample code to use the [LH API](https://developer.lufthansa.com) in Java.
- sample code to build a MVP-based application (with [AppController](http://www.gwtproject.org/articles/mvp-architecture.html#app_controller) and [history management](http://www.gwtproject.org/articles/mvp-architecture.html#history)).

This should be of interest for a wide range of prospective developers: Android native app, webapp and more generally multi-platform mobile HTML5 applications (see, e.g., [PhoneGap](http://phonegap.com/)).


The LH API is queried on the server side (hence  the possible need for proxy setting--see next section). The client invokes the services via [GWT RPCs](http://www.gwtproject.org/doc/latest/tutorial/RPC.html).

##Settings

The file *Showcase-settings.xml* (located in *src\\main\\resources\\lh\\api\\showcase*) can be used to specify the required authentication information (obtained after successfully [registering](https://developer.lufthansa.com/) to the LH API).

Furthermore, if the server is behind a proxy, the parameters can be specified in this setting file as well.

Here an example:  

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<settings>
<authentication>
	<api>
		<key>client id</key>
		<secret>client secret</secret>
		<callback>http://example.com:8080</callback>
	</api>
</authentication>
<proxy>
	<http>
		<port>8080</port>
		<active>true</active>
		<user>username</user>
		<password>password</password>
		<host>proxy host</host>
	</http>
</proxy>
</settings>
```

## Compiling and Testing

After cloning the repository, the following commands should be used:
```sh
mvn clean # can be omitted the first time
mvn gwt:generateAsync
mvn package
```

Then, the application can be launched locally in development mode with the command (see [Compiling and Debugging](http://www.gwtproject.org/doc/latest/DevGuideCompilingAndDebugging.html) for further details):
```sh
mvn gwt:run
```

Note that we assume that [maven](https://maven.apache.org/) is installed (as well as, obviously, an adequate [Java SDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)).

To open the project in [Eclipse](https://eclipse.org/), the following command might prove useful:
```sh
mvn eclipse:eclipse
```

##License

© 2015 Loic Merckel, [Apache v2](https://www.apache.org/licenses/LICENSE-2.0.html) licensed.
