package com.example.a36

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.a36.databinding.FragmentColorListBinding
import com.example.a36.databinding.FragmentColoredBinding
import java.io.IOException

class ColoredFragment : Fragment() {
    val dataModel: SavedData by activityViewModels()
    lateinit var binding: FragmentColoredBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentColoredBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataModel.bgColor.observe(this, Observer{
            dataModel.bgColor.value?.let { it1 -> binding.root.setBackgroundColor(it1) }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = ColoredFragment()
    }
}