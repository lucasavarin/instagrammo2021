package com.costa.utils

import android.content.Context
import com.costa.views.LoaderMain

open class LoadingUtils {
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
}

// Per richiamare il Loader 👇
// LoadingUitls.showDialog(context, isCancelable)
