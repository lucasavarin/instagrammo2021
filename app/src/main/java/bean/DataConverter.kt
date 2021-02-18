package bean

interface DataConverter<R: DataModel, T: ResponseDataModel> {
    fun convert(response: T) : R
}