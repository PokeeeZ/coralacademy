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
class LoginPage : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var dbHandler: DataBaseHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        dbHandler = DataBaseHandler(requireContext())

        val loginSuccess = "Login Successful!"
        val loginSuccessCoral = "Login Successful! Welcome Corallium member!"
        val loginFail = "Login Failed!"

        binding.loginButton.setOnClickListener {

            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()

            val user = dbHandler.readData().find { it.getUser() == username && it.getPass() == password }

            if (user != null) {
                if (binding.checkBox.isChecked && user.getMemStat()) {
                    Toast.makeText(requireContext(), loginSuccessCoral, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_SecondFragment_to_homeScreenCoralFrag)
                } else {
                    Toast.makeText(requireContext(), loginSuccess, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_SecondFragment_to_homeScreenRegularFrag)
                }
            } else {
                Toast.makeText(requireContext(), loginFail, Toast.LENGTH_SHORT).show()
            }
        }

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
