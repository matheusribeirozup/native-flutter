# Native Android project with Flutter modules as dependencies
To add a flutter module as a dependency on a native project, just follow the [documentation](https://flutter.dev/docs/development/add-to-app). However, there are some known limitations, including the impossibility of adding multiple flutter projects in a single native project, described in this [issue](https://github.com/flutter/flutter/issues/39707).
To work around this problem, we created a single module that contains the modules to be added, and we exposed it to the native application. And we use a channel for the host app to inform Flutter when it wants to navigate between them.

### To compile aar follow run the commands below:

Clone this project:
```sh
$ git clone git@github.com:matheusribeirozup/native-flutter.git
```

Go to flutter_module folder:
```sh
$ cd flutter_module/
```

Get dependencies and compile Flutter module:
```sh
$ flutter pub get
$ flutter clean && flutter build aar
```

Go to android_app folder:
```sh
$ cd android_app/
```

Compile native module:
```sh
$ cd ..
$ ./gradlew clean assemble
```

Done. 

### Aar is ready to be used 🎉
It is located in ```flutter_module/build/host/outputs/repo/```
