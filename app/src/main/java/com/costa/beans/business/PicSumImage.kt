package com.costa.beans.business

import com.costa.`interface`.BusinessModel

data class PicSumImage(
    var id: String,
    var author: String,
    var width: Int,
    var height: Int,
    var url: String,
    var download_url: String,
    var imageFormated:String?
): BusinessModel