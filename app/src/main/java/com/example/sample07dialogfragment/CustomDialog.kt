package com.example.sample07dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.sample07dialogfragment.databinding.FragmentCustomDialogBinding

class CustomDialog : DialogFragment() {

    private var _binding: FragmentCustomDialogBinding? = null
    private val binding: FragmentCustomDialogBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCustomDialogBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isCancelable = false

        with(binding) {

            editText.setText(requireArguments().getString(KEY_RETURN))
            button.setOnClickListener {
                parentFragmentManager
                    .setFragmentResult(FirstFragment.KEY_DIALOG,
                    bundleOf(FirstFragment.BUNDLE_KEY to editText.text.toString())
                    )
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    companion object {
        const val KEY_RETURN = "key_return"
    }
}