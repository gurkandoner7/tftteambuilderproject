package com.portal.tftteambuilderproject.utilities.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.portal.tftteambuilder.R
import com.portal.tftteambuilder.databinding.TftContinueButtonBinding

class TftCustomContinueButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var _binding: TftContinueButtonBinding? = null
    private val binding: TftContinueButtonBinding
        get() = _binding!!
    private val attributes =
        context.obtainStyledAttributes(attrs, R.styleable.TftContinueButton)

    init {
        _binding = TftContinueButtonBinding.inflate(LayoutInflater.from(context), this, true)
        binding.apply {
            continueButton.text =
                attributes.getString(R.styleable.TftContinueButton_android_text)

            attributes.getDimension(
                R.styleable.TftContinueButton_android_textSize,
                20f
            ).apply {
                if (this == DEFAULT_BUTTON_TEXT_SIZE) continueButton.textSize = this
                else continueButton.textSize = this / resources.displayMetrics.scaledDensity
            }
        }

        if (attributes.getBoolean(R.styleable.TftContinueButton_setOutlinedButtonStyle, false))
            setOutlinedButtonStyle()


        if (attributes.getBoolean(R.styleable.TftContinueButton_setCancelButtonStyle, false))
            setCancelButtonStyle()
    }

    override fun onDetachedFromWindow() {
        _binding = null
        super.onDetachedFromWindow()
    }

    private fun setCancelButtonStyle() {
        binding.continueButton.apply {
            setTextColor(context.getColor(R.color.dark_sky_blue))
            typeface = ResourcesCompat.getFont(context, R.font.montserrat_medium)
            setBackgroundResource(R.drawable.bg_cancel_button)
        }
    }

    private fun setOutlinedButtonStyle() {
        binding.continueButton.apply {
            setTextColor(context.getColor(R.color.marine_blue))
            typeface = ResourcesCompat.getFont(context, R.font.montserrat_medium)
            setBackgroundResource(R.drawable.bg_outlined_button)
            textSize = 14F
            setPadding(40, 15, 40, 15)
        }
    }

    fun setButtonText(buttonText: String) {
        binding.continueButton.text = buttonText
    }

    fun setButtonOnClick(listener: OnClickListener) {
        binding.continueButton.setOnClickListener(listener)
    }

    fun disableButton() {
        binding.continueButton.apply {
            isEnabled = false
            setBackgroundColor(resources.getColor(R.color.marine_blue_10))
        }
    }

    fun enableButton() {
        binding.continueButton.apply {
            isEnabled = true
            setBackgroundResource(R.drawable.bg_button)
        }
    }

    companion object {
        const val DEFAULT_BUTTON_TEXT_SIZE = 20F
    }
}