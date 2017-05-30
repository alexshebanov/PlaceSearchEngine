package API

interface Response {
    def data()
    String nextPageToken(def data)
    String status(def data)
}