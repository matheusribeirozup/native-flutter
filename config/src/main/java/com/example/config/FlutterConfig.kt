package com.example.config

import android.content.Context
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

object FlutterConfig {

    private const val channelName = "module.flutter/native"
    private lateinit var methodChannel: MethodChannel

    fun warmUp(engineId: String, context: Context) {
        // Instantiate a FlutterEngine
        val flutterEngine = FlutterEngine(context)

        // Start executing Dart code to pre-warm the FlutterEngine
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // Cache the FlutterEngine to be used by FlutterActivity
        FlutterEngineCache.getInstance().put(engineId, flutterEngine)

        // Creates a channel to receive Dart requests
        setMethodCallHandler(flutterEngine)
    }

    private fun setMethodCallHandler(flutterEngine: FlutterEngine) {
        methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, channelName)
        // Receives calls from Dart methods
        methodChannel.setMethodCallHandler { call, result ->
            if (call.method == "getBatteryLevel") {
                result.success(89)
            }
        }
    }

    fun startFlutterActivity(engineId: String, context: Context, route: String) {
        // Start Flutter integration
        context.startActivity(
            FlutterActivity
                .withCachedEngine(engineId)
                .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                .build(context)
        )

        // Calls a specific method in Dart
        methodChannel.invokeMethod("changeRoute", route)
    }
}
