package com.portal.tftteambuilderproject.utilities.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Fragment.showKeyboard(view: View) {
    view.let { activity?.showKeyboard(it) }
}

fun Fragment.findNavControllerSafely(id: Int): NavController? {
    return if (findNavController().currentDestination?.id == id) {
        findNavController()
    } else {
        null
    }
}
fun Context.hideKeyboard(view: View) {
    val inputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showKeyboard(view: View) {
    val inputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED)
}



@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.withClickableSpan(
    colorResId: Int,
    clickablePart: String,
    isUnderlined: Boolean,
    isBoldText: Boolean,
    onClickListener: () -> Unit
) {
    val ss = SpannableString(text)
    val clickableSpan = object : ClickableSpan() {
        override fun updateDrawState(ds: TextPaint) {
            ds.color = ds.linkColor // you can use custom color
            ds.isUnderlineText = isUnderlined // this remove the underline
            ds.isFakeBoldText = isBoldText
        }

        override fun onClick(widget: View) {
            onClickListener.invoke()
        }
    }
    val clickablePartStart = text.indexOf(clickablePart)

    ss.setSpan(
        clickableSpan,
        clickablePartStart, clickablePartStart + clickablePart.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    ss.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(context, colorResId)),
        clickablePartStart, clickablePartStart + clickablePart.length,
        Spanned.SPAN_INCLUSIVE_EXCLUSIVE
    )
    movementMethod = LinkMovementMethod.getInstance()
    setText(ss, TextView.BufferType.SPANNABLE)
    isClickable = true
}

fun TextView.setColoredSpan(colorResId: Int, isBoldText: Boolean, subText: String) {
    val ss = SpannableString(text)
    val subTextStart = text.indexOf(subText)
    ss.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(context, colorResId)),
        subTextStart, subTextStart + subText.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE
    )
    if (isBoldText)
        ss.setSpan(
            StyleSpan(Typeface.BOLD),
            subTextStart, subTextStart + subText.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
    setText(ss, TextView.BufferType.SPANNABLE)
}







