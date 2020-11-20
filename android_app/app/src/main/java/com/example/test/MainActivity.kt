package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.config.FlutterConfig

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<Button>(R.id.btFaq).setOnClickListener {
            FlutterConfig.startFlutterActivity("myId", this, "/faq")
        }
        findViewById<Button>(R.id.btLogin).setOnClickListener {
            FlutterConfig.startFlutterActivity("myId", this, "/login")
        }
    }
}
