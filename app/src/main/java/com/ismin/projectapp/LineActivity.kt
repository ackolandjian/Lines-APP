package com.ismin.projectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LineActivity : AppCompatActivity() {
    private lateinit var floatingButton: FloatingActionButton
    private lateinit var lineMainActivity: Line

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        lineMainActivity = intent.getSerializableExtra("line_info") as Line

        setContentView(R.layout.activity_line)

        val status: TextView = findViewById(R.id.a_line_txv_status)
        status.text = lineMainActivity.status
        val transportmode: TextView = findViewById(R.id.a_line_txv_transportmode)
        transportmode.text = lineMainActivity.transportmode
        val name_line: TextView = findViewById(R.id.a_line_txv_name_line)
        name_line.text = lineMainActivity.name_line
        val shortname_groupoflines: TextView = findViewById(R.id.a_line_txv_shortname_groupoflines)
        shortname_groupoflines.text = lineMainActivity.shortname_groupoflines
        val networkname: TextView = findViewById(R.id.a_line_txv_network)
        networkname.text = lineMainActivity.networkname
        val operatorname: TextView = findViewById(R.id.a_line_txv_operator)
        operatorname.text = lineMainActivity.status
        val accessibility: TextView = findViewById(R.id.a_line_txv_accessibility)
        accessibility.text = lineMainActivity.accessibility
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id:Int = item.itemId
        if (id==android.R.id.home)
            this.finish()
        return true
    }

}