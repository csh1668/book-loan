import axios from "axios";
import ResponseDto from "./responseDto";

const BookApi = () => {
    const baseUrl = "/proxy/api/book";
    const getBook = async (id) => {
        var token = localStorage.getItem("accessToken");
        var config = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };

        console.log('config:', config);
        
    
        const resp = await axios.get(`${baseUrl}/get/${id}`, config);
        try {
            if (resp.status == 200) {
                return ResponseDto.fromPromise(resp.data);
            } else {
                throw new Error(resp.data.message);
            }
        } catch (error) {
            console.error(error);
            throw error;
        }
    };

    const getList = async (page, kw) => {
        const token = localStorage.getItem("accessToken");
        const config = {
            headers: {
                Authorization: `Bearer ${token}`
            },
        };

        var url = `${baseUrl}/list?page=${page}`;
        if (kw) {
            url += `&kw=${kw}`;
        }
        const resp = await axios.get(url, config);
        try {
            if (resp.status == 200) {
                return ResponseDto.fromResponse(resp.data);
            } else {
                throw new Error(resp.data.message);
            }
        } catch (error) {
            console.error(error);
            throw error;
        }
    };

    const fetchBookImage = async (id) => {
        const token = localStorage.getItem("accessToken");
        const config = {
            headers: {
                Authorization: `Bearer ${token}`
            },
            responseType: 'arraybuffer'
        };

        try {
            const resp = await axios.get("/proxy/api/image/download/" + id + ".jpg", config);

            const blob = new Blob([resp.data], { type: "image/jpeg" });
            const url = URL.createObjectURL(blob);

            console.log('url:', url);
            
            return url;
        } catch (error) {
            throw new Error(error);
        }
    }

    return { getBook, getList, fetchBookImage };
}

export default BookApi;