const { Button, Form } = require("react-bootstrap");
const { MyNavbar } = require("./MyNavbar");
const { useState } = require("react");

export default function SignupForm(props) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [password2, setPassword2] = useState('');
    const [email, setEmail] = useState('');

    return (
        <Form onSubmit={(e) => {
            e.preventDefault();
            props.onSignup(username, password, password2, email);
        }} >
            <Form.Group controlId="formBasicUsername">
                <Form.Control type="text" placeholder="Enter username" onChange={(e) => setUsername(e.target.value)} />
            </Form.Group>

            <Form.Group controlId="formBasicPassword">
                <Form.Control type="password" placeholder="Enter password" onChange={(e) => setPassword(e.target.value)} />
            </Form.Group>

            <Form.Group controlId="formBasicPassword">
                <Form.Control type="password" placeholder="Enter password again" onChange={(e) => setPassword2(e.target.value)} />
            </Form.Group>

            <Form.Group controlId="formBasicEmail">
                <Form.Control type="email" placeholder="Enter email" onChange={(e) => setEmail(e.target.value)} />
            </Form.Group>

            <Button variant="primary" type="submit">
                Submit
            </Button>
        </Form>
    )
}
