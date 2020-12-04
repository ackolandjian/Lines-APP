package com.ismin.projectapp

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_LINES = "ARG_LINES"

class LineListFragment : Fragment(), onFavoriteListener {
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var lines: ArrayList<Line>
    private lateinit var rcvLines: RecyclerView
    private lateinit var adapter: LineAdapter
    private var favoriteLines = arrayListOf<Line>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            lines = it.getSerializable(ARG_LINES) as ArrayList<Line>
        }
    }

    override fun unfavFavorite(index: Int){
        if(lines[index] in favoriteLines)
            favoriteLines.remove(lines[index])
        else
            favoriteLines.add(lines[index])
        Toast.makeText(context, lines[index].shortname_groupoflines, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_line_list, container, false)
        val searchField: EditText = rootView.findViewById(R.id.f_line_list_search)

        this.rcvLines=rootView.findViewById(R.id.f_line_list_rcv_lines)
        this.adapter = LineAdapter(lines, listener, context, this)
        this.rcvLines.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(context)
        this.rcvLines.layoutManager=linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        this.rcvLines.addItemDecoration(dividerItemDecoration)

        searchField.addTextChangedListener(object:TextWatcher{
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }
        })

        return rootView;
    }

    fun filter(text: String) {
        val temp = arrayListOf<Line>()
        val lowerText = text.toLowerCase()
        for (d in lines) {
            if (d.name_line.toLowerCase().contains(lowerText) ||
                    d.shortname_groupoflines.toLowerCase().contains(lowerText) ||
                    d.transportmode.toLowerCase().contains(lowerText)) {
                temp.add(d)
            }
        }
        this.adapter.updateList(temp)
    }

    companion object {
        @JvmStatic
        fun newInstance(lines:List<Line>) =
            LineListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_LINES, ArrayList(lines))
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(
                    context.toString() +
                            " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onItemClicked(line:Line)
    }
}
