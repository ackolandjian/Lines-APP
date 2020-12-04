package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable


class MainActivity : AppCompatActivity(), BookCreator, BookListFragment.OnFragmentInteractionListener{
    private val TAG = MainActivity::class.simpleName
    private val bookshelf = Bookshelf()
    private lateinit var bookService: BookService;
  //  val manager = supportFragmentManager
    private val infoFragments: ArrayList<InfoFragment> = ArrayList()

  //  private var bookListFragment: BookListFragment = BookListFragment()
  //  private var infoFragment: InfoFragment = InfoFragment()

    private fun displayList() {
        val bookListFragment = BookListFragment.newInstance(bookshelf.getAllBooks())

        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_lyt_container, bookListFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    override fun onItemClicked(book: Book) {
        val itemIntent = Intent(this, LineActivity::class.java)
        itemIntent.putExtra("line_info", book as Serializable)
        this.startActivity(itemIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = findViewById<Button>(R.id.list)
        val info = findViewById<Button>(R.id.information)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://lignes-ack-cpy.cleverapps.io/")
            .build()


        bookService = retrofit.create(BookService::class.java)

        bookService.getAllBooks().enqueue(object : Callback<ArrayList<Book>> {
            override fun onResponse(
                call: Call<ArrayList<Book>>,
                response: Response<ArrayList<Book>>
            ) {
                val allBooks = response.body()
                allBooks?.forEach {
                    bookshelf.addBook(it)
                }

                displayList()
            }

            override fun onFailure(call: Call<ArrayList<Book>>, t: Throwable) {
                displayErrorToast(t)

            }
        })

        list.setOnClickListener() {
            displayList()
        }

        info.setOnClickListener() {
            displayInfo()
        }

    }

    private fun displayErrorToast(t: Throwable) {
        Toast.makeText(
            applicationContext,
            "Network error ${t.localizedMessage}",
            Toast.LENGTH_LONG
        ).show()
    }


    fun closeInfoFragment(view: View){
        displayList()

        //a_main_btn_creation.visibility = View.VISIBLE
        f_info_button.visibility = View.GONE
    }

    fun goToCreation(view: View) {
        val createBookFragment = CreateBookFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.a_main_lyt_container, createBookFragment)
            .addToBackStack("createBookFragment")
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()

        //a_main_btn_creation.visibility = View.GONE
    }

    fun displayInfo(){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val infoFragment = InfoFragment()

        fragmentTransaction.replace(R.id.a_main_lyt_container, infoFragment)
        fragmentTransaction.commit()
    }

    override fun onBookCreated(book: Book) {
        bookService.createBook(book).enqueue {
            onResponse = {
                bookshelf.addBook(it.body()!!)
                closeCreateFragment()
            }
            onFailure = {
                if (it != null) {
                    displayErrorToast(it)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_information -> {
                displayInfo()
                true
            }
            R.id.action_refresh -> {
                Toast.makeText(baseContext, "Data refreshed !", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun closeCreateFragment() {
        displayList();
    }



}