package com.ismin.projectapp

import java.io.Serializable

class LineController: Serializable {
    private val storage = HashMap<String, Line>()

    fun addLine(line: Line) {
        this.storage[line.shortname_groupoflines] = line
    }

    fun getBook(name_line: String): Line? {
        return this.storage[name_line]
    }

    fun getAllLines():List<Line>{
        return ArrayList(this.storage.values).sortedBy { line -> line.name_line }
    }

    fun getTotalNumberOfLines():Int{
        return this.storage.size
    }
}
