import axios from "axios";
import ResponseDto from "./responseDto";

const LoanApi = () => {
  const loanUrl = "/proxy/api/loan";
  const returnUrl = "/proxy/api/return";

  const loanBook = async (id) => {
    var token = localStorage.getItem("accessToken");
    var config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };

    const resp = await axios.get(`${loanUrl}/${id}`, config);
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

  const checkLoan = async (id) => {
    var token = localStorage.getItem("accessToken");
    var config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };

    const resp = await axios.get(`${loanUrl}/check/${id}`, config);
    try {
      if (resp.status == 200) {
        return resp.data;
      } else {
        throw new Error(resp.data.message);
      }
    } catch (error) {
      console.error(error);
      throw error;
    }
  };

  const returnBook = async (id) => {
    const token = localStorage.getItem("accessToken");
    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    const resp = await axios.get(`${returnUrl}/${id}`, config);
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
        Authorization: `Bearer ${token}`,
      },
      responseType: "arraybuffer",
    };

    try {
      const resp = await axios.get(
        "/proxy/api/image/download/" + id + ".jpg",
        config
      );

      const blob = new Blob([resp.data], { type: "image/jpeg" });
      const url = URL.createObjectURL(blob);

      console.log("url:", url);

      return url;
    } catch (error) {
      throw new Error(error);
    }
  };

  return { loanBook, returnBook, fetchBookImage, checkLoan };
};

export default LoanApi;
