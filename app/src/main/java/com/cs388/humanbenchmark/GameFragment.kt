package com.cs388.humanbenchmark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cs388.humanbenchmark.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ReflexGameStart.setOnClickListener {
            // Start ReflexGameActivity
            val intent = Intent(activity, ReflexGameActivity::class.java)
            startActivity(intent)
        }

        binding.SequenceGameStart.setOnClickListener {
            val intent = Intent(activity, SequenceGameActivity::class.java)
            startActivity(intent)
        }

        binding.AimTrainerGameStart.setOnClickListener {
            val intent = Intent(activity, AimTrainerGameActivity::class.java)
            startActivity(intent)
        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}
