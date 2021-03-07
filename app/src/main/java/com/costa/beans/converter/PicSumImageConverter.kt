package com.costa.beans.converter

import com.costa.`interface`.RestConverter
import com.costa.beans.business.PicSumImage
import com.costa.beans.rest.PicSumImageOut

object PicSumImageConverter: RestConverter<PicSumImageOut, PicSumImage> {
    override fun restToBusiness(restModel: PicSumImageOut): PicSumImage =
        PicSumImage(restModel.id,restModel.author,restModel.width,restModel.height,restModel.url,restModel.download_url, null)

    override fun businessToRest(businessModel: PicSumImage): PicSumImageOut =
        PicSumImageOut(businessModel.id,businessModel.author,businessModel.width,businessModel.height,businessModel.url,businessModel.download_url)

}