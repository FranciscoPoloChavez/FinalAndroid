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
                "Términos usados durante la crisis de salud",
                R.drawable.picto1
            ),
            IntroSlide(
                "Preguntas más frecuentes",
                "Respuestas más relevantes acerca de Sars-Cov-2",
                R.drawable.picto2
            ),
            IntroSlide(
                "Emociones y sentimientos",
                "La salud mental es muy importante hoy en día, asegurate de hablar con alguien de tu confianza cuando te sientas decaído",
                R.drawable.picto3
            ),
            IntroSlide(
                "Maneras de ayudar",
                "Todos debemos poner de nuestra parte para sobrepasar esta situación, todos llevamos un heroe dentro",
                R.drawable.picto5
            ),
            IntroSlide(
                "La importancia de cubrirse nariz y boca",
                "La transmisión del virus es mediante las gotitas que expulsamos al hablar, toser, estornudar y exhalar",
                R.drawable.picto4
            ),
            IntroSlide(
                "Quedate en casa",
                "No arriesgues tu vida si no tienes la necesidad de dejar tu hogar, pronto volveremos a la normalidad",
                R.drawable.aislamiento
            ),
            IntroSlide(
                "Reduce el riesgo de infección",
                "Sigue las recomendaciones de los expertos",
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

