package com.example.networkingwithretrofitchapter5.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.networkingwithretrofitchapter5.R
import com.example.networkingwithretrofitchapter5.databinding.FragmentRegisterBinding
import com.example.networkingwithretrofitchapter5.model.RegisterResponseItem
import com.example.networkingwithretrofitchapter5.network.CarsApi
import com.example.networkingwithretrofitchapter5.request.RegisterRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterFragment : Fragment() {
    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.register.setOnClickListener {
            val registerRequest = RegisterRequest(
                email = binding.inputEmail.text.toString(),
                password = binding.inputPassword.text.toString(),
                "admin"
            )
            CarsApi.retrofitService.registerAdmin(registerRequest).enqueue(object :
                Callback<RegisterResponseItem> {
                override fun onResponse(
                    call: Call<RegisterResponseItem>,
                    response: Response<RegisterResponseItem>
                ) {
                    Log.d("MainAcativity", "ini data register => ${response.body()}")
                }

                override fun onFailure(call: Call<RegisterResponseItem>, t: Throwable) {

                }
            })
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())
        }
    }


}


