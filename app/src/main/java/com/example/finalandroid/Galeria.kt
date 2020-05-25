package com.example.finalandroid

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_galeria.*
import kotlinx.android.synthetic.main.activity_main.*

class Galeria : AppCompatActivity() {

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Sars-Cov-2",
                "Terms used during the health crisis",
                R.drawable.picto1
            ),
            IntroSlide(
                "Frequently asked questions",
                "Most relevant answers about Sars-Cov-2",
                R.drawable.picto2
            ),
            IntroSlide(
                "Emotions and feelings",
                "Mental health is important these days, be sure to talk to someone you trust if you feel down",
                R.drawable.picto3
            ),
            IntroSlide(
                "Ways to help",
                "We all must do our part to overcome this situation, we all have a hero inside",
                R.drawable.picto5
            ),
            IntroSlide(
                "The importance of covering your mouth and nose",
                "The transmission of the virus is through the droplets that we expel when speaking, coughing, sneezing and exhaling",
                R.drawable.picto4
            ),
            IntroSlide(
                "Stay at home",
                "Don't risk your life if you don't have the need to leave your home, we will see our friends soon",
                R.drawable.aislamiento
            ),
            IntroSlide(
                "Reduce your risk of infection",
                "Follow the recommendations of the experts",
                R.drawable.oms
            )
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeria)
        introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        buttonNext.setOnClickListener{
            if(introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount){
                introSliderViewPager.currentItem += 1
            }
            //else{
                //Intent(applicationContext, AnotherActivity::class.java).also{
                  //  startActivity(it)
                //}
            //}
        }
        textSkipIntro.setOnClickListener{
            Intent(applicationContext, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for(i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }

    }

    private fun setCurrentIndicator(index: Int){
        val childCount = indicatorsContainer.childCount
        for(i in 0 until childCount){
            val imageView = indicatorsContainer[i] as ImageView
            if(i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}

