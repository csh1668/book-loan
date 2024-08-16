import { Col, Container, Row } from "react-bootstrap";

export default function Home() {
    const margin = {
        marginTop: '20px',
        marginBottom: '20px'
    };

    return (
        <Container>
            <Row className="justify-content-md-center" align-items-center="true" style={margin}>
                <Col md="auto">
                    <h1>Welcome to Book-Loan    (●'◡'●)</h1>
                    <p>Book-Loan is a service that allows you to borrow books from other users. You can also lend your books to others.</p>
                </Col>
            </Row>
            <Row className="justify-content-md-center" style={margin}>
                <Col md="4">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent consectetur ligula eget mauris rutrum, at porttitor ligula convallis. Phasellus semper justo eu metus tempus sollicitudin sit amet vitae nibh. Pellentesque placerat dictum fringilla. Ut vehicula laoreet libero, vitae euismod urna pulvinar a. Nulla maximus vulputate sem, sed porta mi volutpat dignissim. Quisque non malesuada nisl, quis condimentum augue. In hac habitasse platea dictumst. Maecenas efficitur a odio sit amet posuere. Duis viverra rhoncus dolor quis sollicitudin. Mauris facilisis mollis molestie.
                </Col>
                <Col md="4">
                Nam iaculis accumsan venenatis. Vivamus cursus consectetur mi, eu hendrerit metus iaculis at. Cras lorem magna, faucibus sed tellus ac, hendrerit dapibus massa. Morbi nec pharetra mi. Donec ac mi at sapien porta semper ut sit amet dui. Etiam porta purus non justo pharetra sodales. Ut congue vitae purus ut eleifend. Sed euismod tincidunt dolor.
                </Col>
            </Row>
            <Row className="justify-content-md-center" style={margin}>
                <Col md="4">
                Phasellus accumsan consectetur elementum. Morbi tincidunt lacus a ultrices condimentum. Aenean varius massa et lorem feugiat blandit. Nullam ullamcorper in magna eu pellentesque. Nam in lorem maximus, vestibulum lectus gravida, tincidunt velit. Morbi finibus, nisl a condimentum iaculis, ante felis faucibus est, nec pharetra quam ligula sed elit. Nunc imperdiet, sem tempor sollicitudin commodo, metus nisi suscipit tortor, sed eleifend sem dolor at urna. Fusce ut orci consequat, accumsan tellus at, condimentum mi. Cras mollis ligula ipsum, et placerat tortor consectetur lobortis. Sed lectus mi, vulputate et urna eu, ullamcorper mattis diam. Fusce a sapien condimentum, pellentesque tellus sed, ornare lorem. Integer eleifend metus eros, eu suscipit purus ultrices non.
                </Col>
                <Col md="4">
                In hac habitasse platea dictumst. Proin ornare vitae ligula quis dapibus. Integer mollis non est nec tempor. Morbi magna urna, convallis tristique luctus a, tempus in diam. Etiam pharetra dictum turpis at hendrerit. Duis id suscipit ex. Pellentesque nibh ligula, venenatis in pellentesque ut, dapibus sed elit. Nullam vehicula non risus sit amet suscipit. Mauris et convallis risus, et placerat erat. Proin aliquet ultrices tristique. Donec dictum porta augue, a porttitor nibh vestibulum vitae. Maecenas euismod fermentum sem id fringilla. Maecenas a enim arcu. Cras tincidunt venenatis blandit.
                </Col>
            </Row>

            <Row className="justify-content-md-center" style={margin}>
                <Col md="auto">
                This website is created by SeoHyeon.
                </Col>

            </Row>

        </Container>
    )
}