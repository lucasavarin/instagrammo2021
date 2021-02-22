package bean.business

interface DataConverter<R: DataModel, T: ResponseDataModel> {
    fun convert(response: T) : R
}