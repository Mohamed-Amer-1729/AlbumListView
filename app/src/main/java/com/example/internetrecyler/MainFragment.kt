package com.example.internetrecyler

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internetrecyler.api.RetrofitInstance
import com.example.internetrecyler.data.Post
import com.example.internetrecyler.databinding.FragmentMainBinding
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var postsAdapter: MyAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            val response: Response<List<Post>> = try{
                RetrofitInstance.retrofitApi.getPosts()
            } catch (e: IOException){
                Log.e("Error", "Internet Connection Not found")
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e("Error", "HTTP result is bad")
                return@launchWhenCreated
            }
            if(response.isSuccessful){
                postsAdapter.posts = response.body()!!
            }else{
                Log.e("Error", "Response bad")
            }
        }
        return binding.root
    }
    private fun setupRecyclerView() = binding.RecylerView.apply {
        postsAdapter = MyAdapter()
        adapter = postsAdapter
        layoutManager = LinearLayoutManager(context)
    }
}