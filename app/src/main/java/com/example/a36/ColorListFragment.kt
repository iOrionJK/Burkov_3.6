package com.example.a36

import android.annotation.SuppressLint
import android.content.res.TypedArray
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a36.databinding.FragmentColorListBinding
import kotlinx.coroutines.IO_PARALLELISM_PROPERTY_NAME
import java.io.IOException
import android.R.color
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer


class ColorListFragment : Fragment(), MyRecyclerViewAdapter.OnItemClickListener {

    lateinit var binding: FragmentColorListBinding
    lateinit var test : RecyclerView
    private val dataModel: SavedData by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("ResourceType", "Recycle")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentColorListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inflate the layout for this fragment

        val colorV = resources.obtainTypedArray(R.array.colorValues)
        val colorN = resources.getStringArray(R.array.colorNames)

        if (dataModel.bgColor.value == null){
            dataModel.bgColor.value = 0x00FFFFFF
        }

        dataModel.scrollPosition.observe(this, Observer{
            dataModel.scrollPosition.value?.let { it1 -> binding.recyclerView.scrollToPosition(it1) }
        })

        getData(colorV, colorN)
    }

    override fun onItemClick(position: Int) {
        dataModel.bgColor.value = position
        dataModel.scrollPosition.value = (binding.recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
    }

    @SuppressLint("ResourceType")
    fun getData(colorV: TypedArray, colorN: Array<String>){
        val newArrayList = arrayListOf<ColorBlock>()
        for(i in colorN.indices){
            val person = ColorBlock(colorV.getColor(i, 0xFFF), colorN[i])
            newArrayList.add(person)
        }
        val adapter = MyRecyclerViewAdapter(newArrayList, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

    }

    companion object {

        @JvmStatic fun newInstance() = ColorListFragment()

    }
}