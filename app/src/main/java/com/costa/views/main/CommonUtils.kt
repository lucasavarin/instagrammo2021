package com.costa.views.main
/*
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import androidx.core.graphics.drawable.toDrawable
import com.costa.instagrammo.R

object CommonUtils {

  fun showLoadingDialog(context: Context): Dialog {
       val progressDialog = Dialog(context)

       progressDialog.let {
           it.show()
           it.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
           it.setContentView(R.layout.loading_fragment)
           it.setCancelable(false)
           it.setCanceledOnTouchOutside(false)
           return it
       }
   }
}*/

/*open class LoadingUtils {
    companion object {
        private var loaderMain: LoaderMain? = null
        fun showDialog(
            context: Context?,
            isCancelable: Boolean
        ) {
            hideDialog()
            if (context != null) {
                try {
                    loaderMain = LoaderMain(context)
                    loaderMain?.let { loaderMain->
                        loaderMain.setCanceledOnTouchOutside(true)
                        loaderMain.setCancelable(isCancelable)
                        loaderMain.show()
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        fun hideDialog() {
            if (loaderMain!=null && loaderMain?.isShowing!!) {
                loaderMain = try {
                    loaderMain?.dismiss()
                    null
                } catch (e: Exception) {
                    null
                }
            }
        }

    }
}*/

// Per richiamare il Loader 👇
// LoadingUitls.showDialog(context, isCancelable)
