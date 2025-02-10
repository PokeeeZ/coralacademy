package com.example.coralacademy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.coralacademy.databinding.AdminAccountInfoViewBinding

class CoralAdminPanelFragment : Fragment() {
    private var _binding: AdminAccountInfoViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = AdminAccountInfoViewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val db = DataBaseHandler(context)

        if (binding.userDataTable.childCount == 0) {
            addTableHeader()
        }

        populateUserData(db)

        binding.read.setOnClickListener {
            populateUserData(db)
        }
        binding.update.setOnClickListener {
            db.updateData()
            binding.read.performClick()
            Toast.makeText(context, "User data updated!", Toast.LENGTH_SHORT).show()
        }
        binding.delete.setOnClickListener {
            db.deleteData()
            binding.read.performClick()
            Toast.makeText(context, "User data deleted!", Toast.LENGTH_SHORT).show()
        }
        binding.resetIdTest.setOnClickListener {
            db.resetIDs()
            binding.read.performClick()
            Toast.makeText(context, "IDs reset to zero.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addTableHeader() {
        val context = requireContext()
        val userDataTable: TableLayout = binding.userDataTable

        // Inflate and add the header row
        val headerRow =
            LayoutInflater.from(context).inflate(R.layout.user_data_column, null, false) as TableRow
        val idHeader = headerRow.findViewById<TextView>(R.id.account_id)
        val usernameHeader = headerRow.findViewById<TextView>(R.id.account_username)
        val passwordHeader = headerRow.findViewById<TextView>(R.id.account_password)
        val accountMemberStatusHeader = headerRow.findViewById<TextView>(R.id.account_member_status)

        idHeader.text = "ID"
        usernameHeader.text = "Username"
        passwordHeader.text = "Password"
        accountMemberStatusHeader.text = "Member?"

        // Add the header row to the table
        userDataTable.addView(headerRow)
    }

    private fun populateUserData(db: DataBaseHandler) {

        val context = requireContext()

        // Access the TableLayout using binding
        val userDataTable: TableLayout = binding.userDataTable

        // Remove existing data rows but keep the header row
        val childCount = userDataTable.childCount

        if (childCount > 1) {
            userDataTable.removeViews(1, childCount - 1)
        }

        val userList = db.readData()
        userList.forEach { user ->
            val tableRow =
                LayoutInflater.from(context).inflate(R.layout.user_data_column, null, false)
            val id = tableRow.findViewById<TextView>(R.id.account_id)
            val username = tableRow.findViewById<TextView>(R.id.account_username)
            val password = tableRow.findViewById<TextView>(R.id.account_password)
            val accountMemberStatus =
                tableRow.findViewById<TextView>(R.id.account_member_status)

            id.text = user.getId().toString()
            username.text = user.getUser()
            password.text = user.getPass()
            accountMemberStatus.text = if (user.getMemStat()) "Yes" else "No"

            userDataTable.addView(tableRow)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}