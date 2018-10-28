package com.alexnassif.mobile.tennisbro.Controller


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alexnassif.mobile.tennisbro.Adapters.PlayerListAdapter
import com.alexnassif.mobile.tennisbro.Model.Player
import com.alexnassif.mobile.tennisbro.R
import com.alexnassif.mobile.tennisbro.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_player_list.*


class PlayerListFragment : Fragment() {

    private lateinit var adapter: PlayerListAdapter
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_player_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = PlayerListAdapter(context!!, mutableListOf()){

        }

        playerRecyclerView.adapter = adapter
        playerRecyclerView.setHasFixedSize(true)

        viewModel.getUserList().observe(this, Observer {

            adapter.setList(it)
        })


    }


    companion object {

        fun newInstance(): PlayerListFragment {
            val fragment = PlayerListFragment()
            return fragment
        }
    }
}
