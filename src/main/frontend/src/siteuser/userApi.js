import axios from "axios";

async function registerUser(username, password, email) {
  try {
    const response = await axios.post("/proxy/api/register", {
      username,
      password,
      email,
    });

    if (response.status === 200) {
      return response.data.userName;
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
      return response.data;
    } else {
      throw new Error(response.data.message);
    }
  } catch (error) {
    console.error("Error during user login:", error);
    throw error;
  }
}

export { registerUser, loginUser };
