package com.ismin.projectapp

import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class InfoFragment : Fragment() {
    private var activity: OnFragmentInteractionListener? = null;

    interface OnFragmentInteractionListener {
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_info, container, false)
        val linkTextViewLinkedinAnna = rootView.findViewById<TextView>(R.id.linkedin_hyperlink_anna)
        linkTextViewLinkedinAnna.movementMethod = LinkMovementMethod.getInstance()
        val linkTextViewLinkedinClarisse = rootView.findViewById<TextView>(R.id.hyperlink_linkedin_clarisse)
        linkTextViewLinkedinClarisse.movementMethod = LinkMovementMethod.getInstance()
        val linkTextViewGitAnna = rootView.findViewById<TextView>(R.id.hyperlink_git_anna)
        linkTextViewGitAnna.movementMethod = LinkMovementMethod.getInstance()
        val linkTextViewGitClarisse = rootView.findViewById<TextView>(R.id.hyperlink_git_clarisse)
        linkTextViewGitClarisse.movementMethod = LinkMovementMethod.getInstance()

        // Inflate the layout for this fragment
        return rootView
    }

    override fun onAttach(context: Context) {
        if (context is OnFragmentInteractionListener) {
            activity = context
        }
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }
}
