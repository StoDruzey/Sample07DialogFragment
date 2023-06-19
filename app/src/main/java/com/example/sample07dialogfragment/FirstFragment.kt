package com.example.sample07dialogfragment

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.text.backgroundColor
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.core.text.inSpans
import androidx.fragment.app.Fragment
import com.example.sample07dialogfragment.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFirstBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            val colorValue = ContextCompat.getColor(requireContext(),
                androidx.appcompat.R.color.material_deep_teal_500)

            val message = buildSpannedString {
                color(colorValue) {
                    append("message")
                }
            }

            val neutral = buildSpannedString {
                inSpans(BackgroundColorSpan(colorValue), StyleSpan(Typeface.BOLD)) {
                    append("neutral")
                }
            }

            button1.setOnClickListener {
                AlertDialog.Builder(requireContext())
                    .setTitle(R.string.app_name)
                    .setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.ok) {_, _ ->

                    }
                    .setNegativeButton(android.R.string.cancel) {_, _ ->

                    }
                    .setNeutralButton(neutral) {_, _ ->

                    }
                    .show()
            }

            button2.setOnClickListener {
                val text = textView.text.toString()
                val customDialog = CustomDialog()
                    .apply {
                        arguments = bundleOf(CustomDialog.KEY_RETURN to text)
                    }
                customDialog.show(childFragmentManager, "cust1")
            }

            childFragmentManager
                .setFragmentResultListener(KEY_DIALOG, viewLifecycleOwner) {_, bundle ->
                    textView.text = bundle.getString(BUNDLE_KEY)
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    companion object {
        const val KEY_DIALOG = "key_dialog"
        const val BUNDLE_KEY = "bundle_key"
    }
}