package uz.muhamadaziz.roomcodialapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import uz.muhamadaziz.roomcodialapp.databinding.FragmentAddGroupBinding
import uz.muhamadaziz.roomcodialapp.db.MyDataBase
import uz.muhamadaziz.roomcodialapp.model.Course
import uz.muhamadaziz.roomcodialapp.model.Group
import uz.muhamadaziz.roomcodialapp.model.Teacher


class AddGroupFragment : Fragment() {
    private lateinit var binding: FragmentAddGroupBinding

    private lateinit var codialDatabase: MyDataBase
    private lateinit var teachersList: ArrayList<Teacher>

    private lateinit var course: Course


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        codialDatabase = MyDataBase.getInstance(requireContext())
        teachersList = codialDatabase.teacherDao().getAllTeachers() as ArrayList<Teacher>
        course = arguments?.getSerializable("course") as Course

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentAddGroupBinding.inflate(layoutInflater, container, false)

        val teacherList = ArrayList<String>()
        for (i in teachersList.indices) {
            teacherList.add(teachersList[i].name!!)
        }

        val timesList =
            arrayOf(
                "08:00 -> 10:00",
                "10:00 -> 12:00",
                "14:00 -> 16:00",
                "16:00 -> 18:00",
                "18:00 -> 20:00"
            )

        val daysList = arrayOf("Dushanba, Chorshanba, Juma", "Seshanba, Payshanba, Shanba")

        binding.teacherName.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, teacherList)
        binding.times.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, timesList)
        binding.days.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, daysList)

        binding.save.setOnClickListener {
            val groupTitle: String = binding.groupTitle.text.toString()
            val groupTeacherName: String =
                codialDatabase.teacherDao().getAllTeachers()[binding.teacherName.selectedItemPosition].name!!
            val groupTime: String = timesList[binding.times.selectedItemPosition]
            val groupDay: String = daysList[binding.days.selectedItemPosition]
            val groupTeacherId: Teacher =
                codialDatabase.teacherDao().getAllTeachers()[binding.teacherName.selectedItemPosition]

            codialDatabase.groupDao().insertGroup(
                Group(
                    1,
                    groupTitle,
                    groupTeacherName,
                    groupTime,
                    groupDay,
                    groupTeacherId.id,
                    course.id!!
                )
            )
            Toast.makeText(requireContext(), "Saqlandi", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

        return binding.root
    }

}