package uz.muhamadaziz.roomcodialapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import uz.muhamadaziz.roomcodialapp.R
import uz.muhamadaziz.roomcodialapp.adapter.TeacherRvAdapter
import uz.muhamadaziz.roomcodialapp.adapter.VpAdapter
import uz.muhamadaziz.roomcodialapp.databinding.FragmentGroupHomeBinding
import uz.muhamadaziz.roomcodialapp.model.Course


class GroupHomeFragment : Fragment() {

    private lateinit var binding: FragmentGroupHomeBinding
    private lateinit var adapter: VpAdapter

    private lateinit var course: Course
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        course = arguments?.getSerializable("course") as Course

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      binding = FragmentGroupHomeBinding.inflate(layoutInflater , container, false)
        binding.toolBar.title = course.title

        adapter = VpAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 1) {
                    binding.toolBar.inflateMenu(R.menu.add)
                    binding.toolBar.setOnMenuItemClickListener {
                        findNavController().navigate(
                            R.id.addGroupFragment,
                            bundleOf("course" to course)
                        )
                        true
                    }
                } else {
                    binding.toolBar.menu.clear()
                }
            }
        })
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position -> // Styling each tab here
            if (position == 0) {
                tab.text = "Ochilgan guruhlar"
            } else {
                tab.text = "Ochilayotgan guruhlar"
            }
        }.attach()



        return binding.root
    }

}