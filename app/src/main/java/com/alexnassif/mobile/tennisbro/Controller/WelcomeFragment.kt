package com.alexnassif.mobile.tennisbro.Controller

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alexnassif.mobile.tennisbro.R
import kotlinx.android.synthetic.main.activity_welcome.*


class WelcomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getStartedButton.setOnClickListener {
            val createAccountIntent = Intent(context, CreateAccountActivity::class.java)
            startActivity(createAccountIntent)
        }

        loginBtn.setOnClickListener {
            val loginIntent = Intent(context, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }
    companion object {

        @JvmStatic
        fun newInstance(): WelcomeFragment {
            val fragment = WelcomeFragment()
            return fragment
        }

    }
}
