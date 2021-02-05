package com.monapk.hotpepperapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import coil.load
import com.monapk.hotpepperapi.model.HotPepperApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView: ImageView = findViewById<ImageView>(R.id.imageView)
        val button: Button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            lifecycleScope.launch(context = Dispatchers.IO) {
                val response =
                    HotPepperApiService().getRestaurant(apiKey = getString(R.string.api_key))
                response?.run {
                    withContext(Dispatchers.Main) {
                        for (shop in this@run.results.shop) {
                            imageView.load(shop.photo.pc.l)
                        }
                    }
                }
            }
        }
    }
}
