package com.regadeveloper.detik

import retrofit2.Callback
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.regadeveloper.detik.adapter.RecyclerViewAdapter
import com.regadeveloper.detik.databinding.ActivityMainBinding
import com.regadeveloper.detik.model.ResponseNews
import com.regadeveloper.detik.service.RetrofitBuilder
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val rvAdapter = RecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        binding = ActivityMainBinding.inflate(layoutInflater)
        with(binding){
            setContentView(root)
//            setSupportActionBar(toolBar)
            mainRv.adapter = rvAdapter
            mainRv.layoutManager = LinearLayoutManager(baseContext)
            mainRv.setHasFixedSize(true)
        }

        val call = RetrofitBuilder.getService().fechHeadLines()
        //call enqueue utk mengambil data dari internet
        call.enqueue(object  : Callback<ResponseNews>{
            override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                Timber.e(t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>) {
                Timber.d(response.body()?.totalResults.toString())
                val listArticlesItem = response.body()?.articles
                listArticlesItem.let {
                    it?.let { it1 -> rvAdapter.addData(it1) }
                }
            }
        })
    }
}
