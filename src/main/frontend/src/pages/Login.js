import { useSelector } from "react-redux";
import LoginForm from "../components/LoginForm";
import MyAlert from "../components/MyAlert";
import { useState } from "react";
import { Col, Container, Row } from "react-bootstrap";
const { loginUser } = require("../services/userApi");

export default function Login() {
    const [isOpen, setIsOpen] = useState(false);
    const [msg, setMsg] = useState('');

    const handleLogin = async (username, password) => {
        if (username === '' || password === '') {
            setMsg('아이디와 비밀번호를 입력해주세요.');
            setIsOpen(true);
            return;
        }
        try {
            const response = await loginUser(username, password);            
            const data = response.data;
            localStorage.setItem("accessToken", data.accessToken);
            localStorage.setItem("refreshToken", data.refreshToken);

            console.log('accessToken:', data.accessToken);
            
            window.location.href = '/';
        } catch (error) {
            console.error('Error during user login:', error);
            setMsg(error);
            setIsOpen(true);
        }
    };

    return (
        <>
        <Container className="py-5">
            <Row className="justify-content-md-center">
                <Col md="3">
                    <LoginForm onLogin={handleLogin}></LoginForm>
                </Col>
            </Row>
        </Container>
        <MyAlert isOpen={isOpen} toggleModal={() => {setIsOpen(false);}} title={"오류"} body={msg}></MyAlert>
        </>
    )
}