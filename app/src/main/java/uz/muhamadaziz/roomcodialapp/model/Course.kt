package uz.muhamadaziz.roomcodialapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Course : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var title: String? = null
    var desc: String? = null

    constructor(id: Int?, title: String?, desc: String?) {
        this.id = id
        this.title = title
        this.desc = desc
    }

    constructor(title: String?, desc: String?) {
        this.title = title
        this.desc = desc
    }

    constructor()
}