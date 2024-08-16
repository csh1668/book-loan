import { useSelector } from "react-redux";
import LoginForm from "../components/LoginForm";
import MyAlert from "../components/MyAlert";
import { useState } from "react";
import { Col, Container, Row } from "react-bootstrap";
import SignupForm from "../components/SignupForm";
const { registerUser } = require("../services/userApi");

export default function Signup() {
    const [isOpen, setIsOpen] = useState(false);
    const [msg, setMsg] = useState('');

    const handleSignup = async (username, password, password2, email) => {
        if (username === '' || password === '' || password2 === '' || email === '') {
            setMsg('모든 항목을 입력해주세요.');
            setIsOpen(true);
            return;
        }
        try {
            const response = await registerUser(username, password, email);            
            const data = response.data;
            console.log('Signup response:', data);
            
            
            window.location.href = '/';
        } catch (error) {
            console.error('Error during user signup:', error);
            setMsg(error);
            setIsOpen(true);
        }
    };


    return (
        <>
        <Container className="py-5">
            <Row className="justify-content-md-center">
                <Col md="3">
                <SignupForm onSignup={handleSignup}></SignupForm>
                </Col>
            </Row>
        </Container>
        <MyAlert isOpen={isOpen} toggleModal={() => {setIsOpen(false);}} title={"오류"} body={msg}></MyAlert>
        </>
    )
}