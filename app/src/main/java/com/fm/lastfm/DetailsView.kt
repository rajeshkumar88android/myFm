package com.fm.lastfm

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailsView : AppCompatActivity() {

    lateinit var detailImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_view)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            detailImage = findViewById(R.id.detailImage)

            //get data from intent
            val intent = intent
            val name = intent.getStringExtra("urlImage")
            Log.e("name",""+name)
            Log.v("name",""+name)

            Glide.with(this)
                .load(name)
                .into(detailImage);




        }
    }
}