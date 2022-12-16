package uz.muhamadaziz.roomcodialapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Student {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var name: String? = null
    var surname: String? = null
    var fatherName: String? = null
    var groupId: Int? = null

    constructor(id: Int?, name: String?, surname: String?, fatherName: String?, groupId: Int?) {
        this.id = id
        this.name = name
        this.surname = surname
        this.fatherName = fatherName
        this.groupId = groupId
    }

    constructor(name: String?, surname: String?, fatherName: String?, groupId: Int?) {
        this.name = name
        this.surname = surname
        this.fatherName = fatherName
        this.groupId = groupId
    }

    constructor()
}