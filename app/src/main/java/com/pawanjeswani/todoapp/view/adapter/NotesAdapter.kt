package com.pawanjeswani.todoapp.view.adapter

import android.content.Context
import android.content.Intent
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.ViewSwitcher
import androidx.recyclerview.widget.RecyclerView
import com.pawanjeswani.todoapp.R
import com.pawanjeswani.todoapp.model.dbtables.NoteData
import com.pawanjeswani.todoapp.util.Constants.Companion.NOTE_DATA
import com.pawanjeswani.todoapp.util.Constants.Companion.REQUEST_EDIT
import com.pawanjeswani.todoapp.view.activity.EditNoteActivity
import com.pawanjeswani.todoapp.view.activity.MainActivity
import kotlin.math.roundToInt


class NotesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //notes list will be stored in this var
    private var noteList = mutableListOf<NoteData>()
    //context variable
    var mContext: Context? = null

    constructor(context: Context) {
        this.mContext = context
    }

    fun setNotes(notesList:ArrayList<NoteData>){
        //clearing the existing data
        this.noteList.clear()
        //adding the new data
        noteList.addAll(notesList)
        //notifying the adapter that data is changed
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false);
        return NoteViewHolder(view)
    }


    override fun getItemCount(): Int {
        return noteList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var holderr = holder as NoteViewHolder
        //setting last item's bottom padding
        if (position == noteList.size - 1) {
            holderr.itemView.setPadding(0,0,0,dpToPx(15,mContext!!))
        }
        //passing the respective note data to bind
        holderr.bind(noteList[position])

        holderr.tvNoteTitle.setOnClickListener {
            //user can change the title by clicking on this
            holderr.vwSwTitle.nextView
            holderr.etNoteTitle.requestFocus()
        }

        holderr.itemView.setOnClickListener {
            openEditNoteAct(position)
        }

    }

    private fun openEditNoteAct(position: Int) {
        var intent = Intent(mContext,EditNoteActivity::class.java)
        intent.putExtra(NOTE_DATA,noteList[position])
        (mContext as MainActivity).startActivityForResult(intent,REQUEST_EDIT)
    }

    private open inner class NoteViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvNoteTitle: TextView = itemView.findViewById(R.id.tv_note_title)
        internal var etNoteTitle: EditText = itemView.findViewById(R.id.et_note_title)
        internal var vwSwTitle: ViewSwitcher = itemView.findViewById(R.id.vw_sw_title)
        internal var tvNoteContent: TextView = itemView.findViewById(R.id.tv_note_content)
        internal var tvNoteDate: TextView = itemView.findViewById(R.id.tv_note_date)

        internal fun bind(note: NoteData) {
           tvNoteContent.text = note.noteContent
           tvNoteDate.text = note.date
        }
    }

    fun dpToPx(dp: Int, context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }
}