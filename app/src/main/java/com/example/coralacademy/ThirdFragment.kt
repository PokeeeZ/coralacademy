package com.example.coralacademy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.coralacademy.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createAccountButton.setOnClickListener {
            val context = requireContext()

            val username = binding.createUsername.text.toString()
            val password = binding.createAccountPassword.text.toString()
            val passwordCheck = binding.createAccountPasswordCheck.text.toString()

            if (username.isNotEmpty() && password.length >= 6 && password == passwordCheck) {
                val user = User(id, username, password, coralMemberStatus = false)
                val db = DataBaseHandler(context)
                db.insertData(user)

                findNavController().navigate(R.id.action_ThirdFragment_to_homeScreenFrag)

            } else if (username.isNotEmpty() && password.isEmpty() || passwordCheck.isEmpty()) {
                Toast.makeText(
                    context,
                    "Please make sure your password is the same for both boxes!",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (username.isNotEmpty() && password.length < 6) {
                Toast.makeText(
                    context,
                    "Please make sure your password is the correct length!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(context, "Please fill in all the boxes!", Toast.LENGTH_SHORT).show()
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}