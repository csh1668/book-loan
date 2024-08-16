import axios from "axios";
import ResponseDto from "./responseDto";

async function registerUser(username, password, email) {
  try {
    const response = await axios.post("/proxy/api/auth/signup", {
      username,
      password,
      email,
    });

    if (response.status === 200) {
      return ResponseDto.fromResponse(response.data);
    } else {
      throw new Error(response.data.message);
    }
  } catch (error) {
    console.error("Error during user registration:", error);
    throw error;
  }
}

async function loginUser(username, password) {
  try {
    const response = await axios.post("/proxy/api/auth/login", {
      username,
      password,
    });

    if (response.status === 200) {
      console.log("Login response:", response.data);

      return ResponseDto.fromResponse(response.data);
    } else {
      throw new Error(response.data.message);
    }
  } catch (error) {
    console.error("Error during user login:", error);
    throw error;
  }
}

async function getMe() {
  try {
    const token = localStorage.getItem("accessToken");
    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };

    const response = await axios.get("/proxy/api/user/me", config);
    return ResponseDto.fromResponse(response.data);
  } catch (error) {}
}

export { registerUser, loginUser, getMe };
