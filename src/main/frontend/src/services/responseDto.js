class ResponseDto {
    constructor(success, message, data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    static fromPromise(promise) {
        return promise
            .then(data => new ResponseDto(true, "", data))
            .catch(error => new ResponseDto(false, error.message, null));
    }

    static fromResponse(response) {
        const { success, message, data } = response;
        return new ResponseDto(success, message, data);
    }
}

module.exports = ResponseDto;