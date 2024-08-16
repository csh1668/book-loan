const { Alert, Button } = require("react-bootstrap");

function AlertDismissible() {
    const [show, setShow] = useState(true);

    return (
        <>
            <Alert show={show} variant="error">
                <Alert.Heading>My Alert</Alert.Heading>
                <p>
                    This is a custom alert that I made myself.
                </p>
                <hr />
                <div className="d-flex justify-content-end">
                    <Button variant="outline-danger" onClick={() => setShow(false)}>
                        Close
                    </Button>
                </div>
            </Alert>
        </>
    )
}