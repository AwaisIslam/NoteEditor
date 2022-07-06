package com.ak.noteeditor.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ak.noteeditor.R
import com.ak.noteeditor.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = getString(R.string.app_name)
        binding.webviewAbout.loadUrl("file:///android_asset/about.html")
    }
}