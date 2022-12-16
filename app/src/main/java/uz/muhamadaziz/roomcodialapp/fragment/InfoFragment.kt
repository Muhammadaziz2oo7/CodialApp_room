package uz.muhamadaziz.roomcodialapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.muhamadaziz.roomcodialapp.databinding.FragmentInfoBinding
import uz.muhamadaziz.roomcodialapp.model.Course

class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    private lateinit var course: Course

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        course = arguments?.getSerializable("course") as Course
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(layoutInflater, container, false)

        binding.toolbar.title = course.title
        binding.text.text = course.desc

        return binding.root
    }
}