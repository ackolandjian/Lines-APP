package com.ismin.android

import java.io.Serializable

class Bookshelf : Serializable {
    private val storage = HashMap<String, Book>()

    fun addBook(book: Book) {
        this.storage[book.shortname_groupoflines] = book
    }

    fun getBook(title: String): Book? {
        return this.storage[title]
    }

    fun getAllBooks(): List<Book> {
        return ArrayList(this.storage.values).sortedBy { book -> book.shortname_groupoflines }
    }

   // fun getBooksOf(author: String): List<Book> {
   //     val filteredStorage = this.storage.filter { it.value.author == author }
   //     return ArrayList(filteredStorage.values).sortedBy { book -> book.shortname_groupoflines }
   // }

    fun getTotalNumberOfBooks(): Int {
        return this.storage.size
    }

}
