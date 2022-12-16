package uz.muhamadaziz.roomcodialapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.muhamadaziz.roomcodialapp.R
import uz.muhamadaziz.roomcodialapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        binding.courses.setOnClickListener {
            findNavController().navigate(
                R.id.coursesFragment,
                bundleOf("query" to 1)
            )
        }

        binding.groups.setOnClickListener {
            findNavController().navigate(
                R.id.coursesFragment,
                bundleOf("query" to 2)
            )
        }

        binding.teachers.setOnClickListener {
            findNavController().navigate(
                R.id.coursesFragment,
                bundleOf("query" to 3)
            )
        }


        return binding.root
    }
}