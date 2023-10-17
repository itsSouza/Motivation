package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        handleUserName()
        handleFilter(R.id.image_all)
        handlePhrase()

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase){

            handlePhrase()

        }else if (view.id in listOf(R.id.image_happy, R.id.image_all, R.id.image_sunny)){
            handleFilter(view.id)
        }

    }

    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.purple_dark))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.purple_dark))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.purple_dark))

        if (id == R.id.image_all){
            binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
            categoryId = MotivationConstants.PHRASEFILTER.ALL
        }else if(id == R.id.image_happy){
            binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
            categoryId = MotivationConstants.PHRASEFILTER.HAPPY
        }else if(id == R.id.image_sunny){
            binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
            categoryId = MotivationConstants.PHRASEFILTER.SUNNY

        }
    }

    private fun handleUserName(){

        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)

        val hello = getString(R.string.hello_user)
        binding.textName.text = "$hello, $name!"

    }

    private fun handlePhrase(){

        binding.textPhrases.text = Mock().getPhrase(categoryId,Locale.getDefault().language)


    }


}