# ServerNotify

ServerNotify is a simple Minecraft mod that notifies you when someone joins or leaves the server. It is built with Fabric.

## Features

- Notifies when a player joins the server
- Notifies when a player leaves the server

## Requirements

- Minecraft 1.20.6
- Fabric Loader 0.15.11
- Java 21
- Fabric API
- Python 3

## Building

### Python (Client Notification)

Activate the virtual environment by running the following command:

```sh
source notification_listener_env/bin/activate
```

Or on Windows:

```bat
notification_listener_env\Scripts\activate
```

Then, run the following command to install the required dependencies:

```sh
pip install -r requirements.txt
```

To build the project, run the following command:

```sh
python notification_listener.py
```

### Java (Server Mod)

To build the project, use the Gradle wrapper scripts provided in the root directory of the project.

On Unix-based platforms like Linux and macOS, open your terminal and navigate to the root directory of the project, then run:

```sh
./gradlew build
```

On Windows, open your command prompt and navigate to the root directory of the project, then run:

```bat
gradlew build
```

The compiled JAR file will be located in the `build/libs` directory.
