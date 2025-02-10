package com.example.coralacademy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.coralacademy.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        val loginSuccess = "Login Successful!"
        val loginSuccessCoral = "Login Successful! Welcome Corallium member!"
        val loginFail = "Login Failed!"

        binding.loginButton.setOnClickListener(View.OnClickListener {
            if (binding.usernameInput.text.toString() == "user" && binding.passwordInput.text.toString() == "1234") {
                if (binding.checkBox.isChecked) {
                    Toast.makeText(requireContext(), loginSuccessCoral, Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(R.id.action_SecondFragment_to_homeScreenFrag)
                } else {
                    Toast.makeText(requireContext(), loginSuccess, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_SecondFragment_to_homeScreenFrag)
                }
            } else {
                Toast.makeText(requireContext(), loginFail, Toast.LENGTH_SHORT).show()
            }

        })

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.createAccount.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
