package com.app.slidesum.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.slidesum.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(layoutInflater)
        return binding.root
    }
}
    /*
    binding.shareGame.setOnClickListener {
                val appName = getString(R.string.app_name)
                val textIntent = Intent(Intent.ACTION_SEND)
                textIntent.type = "text/plain"

                textIntent.putExtra(Intent.EXTRA_SUBJECT, appName)
                val shareText = getString(R.string.share_app_text, requireActivity().packageName)
                textIntent.putExtra(Intent.EXTRA_TEXT, shareText)

                val chooserIntent = Intent.createChooser(textIntent, appName)
                startActivity(chooserIntent)
            }
            binding.rateGame.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(resources.getString(R.string.play_store_link,requireActivity().packageName))
                    )
                )
            }
     */