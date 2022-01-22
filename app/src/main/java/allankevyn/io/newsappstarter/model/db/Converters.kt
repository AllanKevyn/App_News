package allankevyn.io.newsappstarter.model.db

import androidx.room.TypeConverter
import allankevyn.io.newsappstarter.model.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source) : String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name, name)
    }
}