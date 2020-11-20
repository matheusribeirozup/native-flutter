import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_faq/main.dart';
import 'package:flutter_login/main.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      // title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {

  MyHomePage({Key key}) : super(key: key);

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const methodChannel = const MethodChannel('module.flutter/native');

  String _batteryLevel = 'Unknown battery level.';

  Future<void> _getBatteryLevel() async {
    String batteryLevel;
    try {
      final int result = await methodChannel.invokeMethod('getBatteryLevel');
      batteryLevel = 'Battery level at $result % .';
    } on PlatformException catch (e) {
      batteryLevel = "Failed to get battery level: '${e.message}'.";
    }

    setState(() {
      _batteryLevel = batteryLevel;
    });
  }

  Widget stateWidget = Scaffold(
      body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              Text("Did you forget to specify a route?")
            ],
          )
      )
  );

  Future<dynamic> _handleMethod(MethodCall call) async {
    if (call.method == "changeRoute") {
      switch(call.arguments) {
        case "/faq":
          setState(() {
            stateWidget = FaqApp();
          });
          break;
        case "/login":
          setState(() {
            stateWidget = LoginApp();
          });
          break;
      }
    }
  }

  @override
  void initState() {
    super.initState();
    methodChannel.setMethodCallHandler(_handleMethod);
  }

  @override
  Widget build(BuildContext context) {
    return stateWidget;
  }
}
