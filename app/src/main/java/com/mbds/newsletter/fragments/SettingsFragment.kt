package com.mbds.newsletter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.ActivityCompat.recreate
import com.mbds.newsletter.MainActivity
import com.mbds.newsletter.R

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTheme(view, false)
        val lightSwitch = view.findViewById<SwitchCompat>(R.id.lightTheme)
        val darkSwitch = view.findViewById<SwitchCompat>(R.id.darkTheme)
        lightSwitch.setOnCheckedChangeListener { _, isChecked ->
            setTheme(view, isChecked)
        }
        darkSwitch.setOnCheckedChangeListener { _, isChecked ->
            setTheme(view, !isChecked)
        }

    }

    fun setTheme(view: View, isLightTheme: Boolean) {
        val theme = if (isLightTheme) android.R.style.Theme_Light else android.R.style.Theme_Black
        view.findViewById<SwitchCompat>(R.id.lightTheme).isChecked = isLightTheme
        view.findViewById<SwitchCompat>(R.id.darkTheme).isChecked = !isLightTheme
        .setTheme(theme)
        activity?.recreate()
    }

}