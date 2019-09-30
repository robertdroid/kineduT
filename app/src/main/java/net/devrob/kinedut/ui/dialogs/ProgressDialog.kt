package net.devrob.kinedut.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import net.devrob.kinedut.R

class ProgressDialog(context: Context) : Dialog(context) {
    companion object {
        fun show(context: Context): ProgressDialog {
            val dialog = ProgressDialog(context)
            dialog.show()
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.circular_progress_bar)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
    }
}