package com.example.laba4

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laba4.databinding.AsyncFragmentBinding
import java.util.concurrent.TimeUnit

class AsyncFragment : Fragment(), TaskCallbacks {

    companion object {
        const val MyTag = "FRAGMENTASYNC"
    }

    private var adapter: PersonAdapter = PersonAdapter()
    private var MyAsyncTaskText: MyAsyncTask? = null
    private var handler: Handler? = null
    private var callbacks: TaskCallbacks? = null
    private var listText: MutableList<String> = mutableListOf()
    private lateinit var binding: AsyncFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = AsyncFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        callbacks = this
        startTask()
    }
    private fun startTask() {
        MyAsyncTaskText = MyAsyncTask()
        val callback = Handler.Callback { msg ->
            callbacks?.onPostExecute("Съедено очень вкусных пельмешек: " + msg.what.toString())
            false
        }
        handler = Handler(callback)
        MyAsyncTaskText!!.execute()

    }

    private val verticalLinearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
    }
    private fun setupRecycleView(){
        binding.cardPerson.layoutManager = verticalLinearLayoutManager
        binding.cardPerson.adapter = adapter

    }

    @SuppressLint("StaticFieldLeak")
    inner class MyAsyncTask : AsyncTask<Unit, Int, Unit>() {
        override fun onPreExecute() {
            callbacks?.onPreExecuted()
        }

        override fun doInBackground(vararg params: Unit?) {
            Log.d("Started Async", "I'm Started")
            for (i in 0..2) {
                TimeUnit.SECONDS.sleep(1)
                isCancelled && break
            }
        }

        override fun onPostExecute(result: Unit?) {
            callbacks?.let {
                for (i in 1..100) {
                    handler?.sendEmptyMessageDelayed(i, ((i - 1) * 2000).toLong())
                }
            }
        }
    }


    override fun onPreExecuted() {
        Log.d("cancel", "executed")
    }

    override fun onCancelled() {
        Log.d("cancel", "cancel")
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onPostExecute(i: String) {
        listText.add(i)
        adapter.list = listText
        adapter.notifyDataSetChanged()
        Log.d("MESSAGE", i)
    }

}