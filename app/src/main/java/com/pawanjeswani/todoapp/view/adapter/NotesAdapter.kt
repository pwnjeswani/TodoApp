package com.pawanjeswani.todoapp.view.adapter

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pawanjeswani.todoapp.R
import com.pawanjeswani.todoapp.model.dbtables.NoteData
import kotlin.math.roundToInt


class NotesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private var noteList = mutableListOf<NoteData>()
    var mContext: Context? = null


    constructor(context: Context) {
        this.mContext = context
    }

    fun setNotes(notesList:ArrayList<NoteData>){
        this.noteList.clear()
        noteList.addAll(notesList)
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
        if (position == noteList.size - 1) {
            holderr.itemView.setPadding(0,0,0,dpToPx(15,mContext!!))
        }
        holderr.bind(noteList[position])
    }

    private open inner class NoteViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal var tvNoteTitle: TextView = itemView.findViewById(R.id.tv_note_title)
        internal var tvNoteContent: TextView = itemView.findViewById(R.id.tv_note_content)
        internal var tvNoteDate: TextView = itemView.findViewById(R.id.tv_note_date)

        internal fun bind(note: NoteData) {
           tvNoteTitle.text = "Title"
           tvNoteContent.text = note.noteContent
           tvNoteDate.text = note.date
        }
    }

    fun dpToPx(dp: Int, context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }
}