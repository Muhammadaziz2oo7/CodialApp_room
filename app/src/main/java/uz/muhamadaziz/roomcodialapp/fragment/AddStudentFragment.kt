package uz.muhamadaziz.roomcodialapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.muhamadaziz.roomcodialapp.databinding.FragmentAddStudentBinding
import uz.muhamadaziz.roomcodialapp.db.MyDataBase
import uz.muhamadaziz.roomcodialapp.model.Group
import uz.muhamadaziz.roomcodialapp.model.Student

class AddStudentFragment : Fragment() {


    private lateinit var binding: FragmentAddStudentBinding
    private lateinit var codialDatabase: MyDataBase
    private var groupId: Int? = null
    private lateinit var group: Group

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        codialDatabase = MyDataBase.getInstance(requireContext())
        groupId = arguments?.getInt("groupId")
        group = codialDatabase.groupDao().getGroupById(groupId!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddStudentBinding.inflate(layoutInflater, container, false)

        binding.save.setOnClickListener {
            val name: String = binding.name.text.toString()
            val surname: String = binding.surname.text.toString()
            val fatherName: String = binding.fatherName.text.toString()

            codialDatabase.studentDao().insertStudent(Student(name, surname, fatherName, group.id))
            findNavController().popBackStack()
        }

        return binding.root
        }

}