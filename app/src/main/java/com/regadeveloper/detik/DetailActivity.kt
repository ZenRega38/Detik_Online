package com.regadeveloper.detik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.api.load
import com.regadeveloper.detik.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    companion object {
        const val date = "date"
        const val content = "content"
        const val image = "image"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
//        binding = ActivityDetailBinding.inflate(layoutInflater)
//        with(binding) {

            val tanggal = intent.getStringExtra(date)
            val contents = intent.getStringExtra(content)
            val imageDetail = intent.getStringExtra(image)

            txtDate.text = tanggal
            txtContent.text = contents
            img_detail.load(imageDetail)
//        }

    }
}