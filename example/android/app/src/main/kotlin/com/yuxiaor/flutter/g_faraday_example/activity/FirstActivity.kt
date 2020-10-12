package com.yuxiaor.flutter.g_faraday_example.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.yuxiaor.flutter.g_faraday.openFlutter
import com.yuxiaor.flutter.g_faraday.openFlutterForResult
import com.yuxiaor.flutter.g_faraday_example.R

/**
 * Author: Edward
 * Date: 2020-09-03
 * Description:
 */
class FirstActivity : AppCompatActivity() {

    private val infoTxt by lazy { findViewById<TextView>(R.id.infoTxt) }
    private val btn1 by lazy { findViewById<Button>(R.id.btn1) }
    private val btn2 by lazy { findViewById<Button>(R.id.btn2) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //args form flutter
        val argsFromFlutter = hashMapOf<String, Any?>()
        intent?.extras?.keySet()?.forEach {
            argsFromFlutter[it] = intent.extras?.get(it)
        }
        infoTxt.append("flutter 传过来的参数：${argsFromFlutter}")

        //open flutter for result
        btn1.setOnClickListener {
            openFlutterForResult("first_page", 1, "data" to "data form native FirstActivity")
        }

        //pop to flutter
        btn2.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().apply { putExtra("data", "data form android first activity") })
            finish()
        }

    }

    /**
     * on flutter result
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val map = hashMapOf<String, Any?>()
        data?.extras?.keySet()?.forEach {
            map[it] = data.extras?.get(it)
        }

        infoTxt.append("\n Flutter 返回的数据：${map}")
    }
}