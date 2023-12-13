package com.portal.tftteambuilderproject.utilities.customviews

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.portal.tftteambuilder.databinding.CustomToastBinding

class TftCustomToast {
    companion object {
        fun showToast(context: Context, message: String, view: View) {
            val inflater = LayoutInflater.from(context)
            val binding = CustomToastBinding.inflate(inflater)

            binding.tvToast.setText(message)
            val location = IntArray(2)
            view.getLocationInWindow(location)
            val viewX = location[0]
            val viewY = location[1] - view.height - 24

            val toast = Toast(context)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = binding.root
            toast.setGravity(Gravity.START or Gravity.TOP, viewX, viewY)
            toast.show()
        }
    }
}