package uz.muhamadaziz.roomcodialapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.muhamadaziz.roomcodialapp.R
import uz.muhamadaziz.roomcodialapp.adapter.StudentRvAdapter
import uz.muhamadaziz.roomcodialapp.databinding.EditStudentBinding
import uz.muhamadaziz.roomcodialapp.databinding.FragmentStudentsBinding
import uz.muhamadaziz.roomcodialapp.databinding.TeacherAddBinding
import uz.muhamadaziz.roomcodialapp.db.MyDataBase
import uz.muhamadaziz.roomcodialapp.model.Group
import uz.muhamadaziz.roomcodialapp.model.Student


class StudentsFragment : Fragment() {


    private lateinit var binding: FragmentStudentsBinding
    private lateinit var group: Group
    private var groupId: Int? = null
    private var groupTitle: String? = null
    private var groupTime: String? = null

    private lateinit var codialDatabase: MyDataBase
    private lateinit var adapter: StudentRvAdapter
    private lateinit var studentsList: ArrayList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        codialDatabase = MyDataBase.getInstance(requireContext())
        groupId = arguments?.getInt("groupId")
        groupTitle = arguments?.getString("groupTitle")
        groupTime = arguments?.getString("groupTime")
        group = codialDatabase.groupDao().getGroupById(groupId!!)
    }


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentsBinding.inflate(layoutInflater, container, false)
        binding.toolBar.title = groupTitle
        binding.toolBar.setOnMenuItemClickListener {
            findNavController().navigate(R.id.addStudentFragment, bundleOf("groupId" to groupId))
            true
        }
        studentsList = codialDatabase.studentDao().getAllStudents() as ArrayList<Student>

        val studentList: ArrayList<Student> = ArrayList()
        for (i in 0 until studentsList.size) {
            if (studentsList[i].groupId == groupId) {
                studentList.add(studentsList[i])
            }
        }

        binding.groupTitle.text = groupTitle
        binding.groupDesc.text = "O'quvchilar soni: ${studentList.size}"
        binding.groupTime.text = groupTime

        adapter = StudentRvAdapter(object : StudentRvAdapter.OnItemClick {
            override fun onItemClick(student: Student, position: Int) {

            }

            override fun onItemEditClick(student: Student, position: Int) {
                val alertDialog = AlertDialog.Builder(requireContext()).create()

                val addStudent =
                    EditStudentBinding.inflate(LayoutInflater.from(requireContext()), null, false)

                alertDialog.setView(addStudent.root)

                addStudent.name.setText(student.name)
                addStudent.surname.setText(student.surname)
                addStudent.fatherName.setText(student.fatherName)

                addStudent.update.setOnClickListener {
                    val name: String = addStudent.name.text.toString()
                    val surname: String = addStudent.surname.text.toString()
                    val fatherName: String = addStudent.fatherName.text.toString()

                    student.name = name
                    student.surname = surname
                    student.fatherName = fatherName
                    codialDatabase.studentDao().updateStudent(student)
                    adapter.notifyItemChanged(studentList.size)
                    adapter.notifyItemRangeChanged(position, studentList.size)
                    alertDialog.dismiss()
                }

                addStudent.close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }

            override fun onItemDeleteClick(student: Student, position: Int) {

                val alertDialog = AlertDialog.Builder(requireContext())

                alertDialog.setTitle("Eslatma!")

                alertDialog.setMessage("Rostan ham o'chirmoqchimisiz?")

                alertDialog.setPositiveButton("Ha") { _, _ ->
                    codialDatabase.studentDao().deleteStudent(student)
                    studentList.remove(student)
                    adapter.notifyItemChanged(studentList.size)
                    adapter.notifyItemRangeChanged(position, studentList.size)
                    alertDialog.create().dismiss()
                }
                alertDialog.setNegativeButton("Yo'q") { _, _ ->
                    alertDialog.create().dismiss()
                }
                alertDialog.show()
            }
        }, studentList)
        binding.recyclerView.adapter = adapter

        if (group.groupPosition == 0) {
            binding.startLesson.visibility = View.GONE
        }

        binding.startLesson.setOnClickListener {
            binding.startLesson.visibility = View.GONE
            group.groupPosition = 0

            codialDatabase.groupDao().updateGroup(group)
        }

        return binding.root
    }
}
