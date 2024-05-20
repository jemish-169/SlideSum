package com.app.slidesum.view.fragment

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.slidesum.R
import com.app.slidesum.databinding.FragmentSettingBinding
import com.app.slidesum.localdata.LocalData

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(layoutInflater)
        loadViews()
        return binding.root
    }

    private fun loadViews() {
        val themeNames = arrayOf(
            getString(R.string.glass_theme),
            getString(R.string.bubble_theme),
            getString(R.string.pink_monochrome),
            getString(R.string.border_blue_ocean),
            getString(R.string.pastel_color)
        )
        var versionName = ""
        try {
            val packageInfo =
                requireActivity().packageManager.getPackageInfo(requireActivity().packageName, 0)
            versionName = packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        binding.settingVersionName.text = versionName
        binding.themeName.text = themeNames[LocalData.getTheme() - 1]
        binding.themeNameFl.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_themeFragment)
        }
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.settingsLlShareApp.setOnClickListener {
            val appName = getString(R.string.app_name)
            val textIntent = Intent(Intent.ACTION_SEND)
            textIntent.type = "text/plain"

            textIntent.putExtra(Intent.EXTRA_SUBJECT, appName)
            val shareText = getString(R.string.share_app_text, requireActivity().packageName)
            textIntent.putExtra(Intent.EXTRA_TEXT, shareText)

            val chooserIntent = Intent.createChooser(textIntent, appName)
            startActivity(chooserIntent)
        }
        binding.settingsLlPrivacyPolicy.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                        resources.getString(R.string.privacy_policy_link)
                    )
                )
            )
        }
        binding.settingsLlRateApp.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                        resources.getString(R.string.play_store_link, requireActivity().packageName)
                    )
                )
            )
        }
    }
}
