import { Link } from "react-router-dom";
const { Button, Form } = require("react-bootstrap");
const { MyNavbar } = require("./MyNavbar");
const { useState } = require("react");

export default function LoginForm(props) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    return (
        <>
        <div style={{ display: "flex", flexDirection: "column" }}>
            <Form onSubmit={(e) => {
                e.preventDefault();
                props.onLogin(username, password);
            }}>
                <Form.Group controlId="formBasicUsername">
                    <Form.Control type="text" placeholder="Enter username" onChange={(e) => setUsername(e.target.value)} />
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                    <Form.Control type="password" placeholder="Enter password" onChange={(e) => setPassword(e.target.value)} />
                </Form.Group>

                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
            <Link to={'/signup'} style={{ alignSelf: "flex-end" }}>Sign up</Link>
        </div>
        </>
    )
}
