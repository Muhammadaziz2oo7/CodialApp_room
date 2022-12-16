package uz.muhamadaziz.roomcodialapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.muhamadaziz.roomcodialapp.Object.courseId
import uz.muhamadaziz.roomcodialapp.R
import uz.muhamadaziz.roomcodialapp.adapter.CourseRvAdapter
import uz.muhamadaziz.roomcodialapp.databinding.CourseAddBinding
import uz.muhamadaziz.roomcodialapp.databinding.FragmentCoursesBinding
import uz.muhamadaziz.roomcodialapp.db.MyDataBase
import uz.muhamadaziz.roomcodialapp.model.Course

class CoursesFragment : Fragment() {
    private lateinit var binding: FragmentCoursesBinding
    private var query: Int? = null
    private lateinit var codialDatabase: MyDataBase
    private lateinit var coursesList: ArrayList<Course>
    private lateinit var adapter: CourseRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        query = arguments?.getInt("query")
        codialDatabase = MyDataBase.getInstance(requireContext())
        coursesList = codialDatabase.courseDao().getAllCourse() as ArrayList<Course>
        adapter = CourseRvAdapter(object : CourseRvAdapter.OnItemClick {
            override fun onItemClick(course: Course, position: Int) {
                courseId = course.id
                when (query) {
                    1 -> {
                        findNavController().navigate(
                            R.id.infoFragment,
                            bundleOf("course" to course)
                        )
                    }
                    2 -> {
                        findNavController().navigate(
                            R.id.groupHomeFragment,
                            bundleOf("course" to course)
                        )
                    }
                    3 -> {
                        findNavController().navigate(
                            R.id.teachersFragment,
                            bundleOf("course" to course)
                        )
                    }
                }
            }
        }, coursesList)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoursesBinding.inflate(layoutInflater, container, false)

        when (query) {
            1 -> {
                binding.toolBar.inflateMenu(R.menu.add)
                binding.toolBar.setOnMenuItemClickListener {
                    val alertDialog = AlertDialog.Builder(requireContext()).create()

                    val addDialog =
                        CourseAddBinding.inflate(LayoutInflater.from(requireContext()), null, false)

                    alertDialog.setView(addDialog.root)

                    addDialog.add.setOnClickListener {
                        val courseTitle: String = addDialog.courseTitle.text.toString()
                        val courseDesc: String = addDialog.courseDesc.text.toString()
                        val course = Course(courseTitle, courseDesc)
                        codialDatabase.courseDao().insertCourse(course)
                        coursesList.add(course)
                        adapter.notifyItemInserted(coursesList.size)

                        alertDialog.dismiss()
                    }

                    addDialog.close.setOnClickListener {
                        alertDialog.dismiss()
                    }

                    alertDialog.show()
                    true
                }
            }
        }
        binding.recyclerView.adapter = adapter
        return binding.root
    }
}