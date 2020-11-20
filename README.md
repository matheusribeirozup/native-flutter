# Android with Flutter
Native Android project with Flutter modules as dependencies.

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

Compile native module:
```sh
$ cd ..
$ ./gradlew clean assemble
```

Done. 

### Aar is ready to be used 🎉
It is located in ```flutter_module/build/host/outputs/repo/```
