import { Button, Col, Container, Form, Nav, Navbar, Row } from 'react-bootstrap';
import logo from '../logo.svg';
import { useEffect, useState } from 'react';
import { useParams, useSearchParams } from 'react-router-dom';
import { getMe, me } from '../services/userApi';

export default function MyNavbar(props) {
    const [search, setSearch] = useState('');
    const [me, setMe] = useState(null);
    const [searchParams] = useSearchParams();

    useEffect(() => {
        const kw = searchParams.get('kw');
        console.log('useeffect kw:', kw);
        
        setSearch(kw ?? '');

        getMe().then((resp) => {
            console.log('me:', resp);
            setMe(resp.data.userName);
        }).catch((error) => {
            console.error('Error during fetching me:', error);
        });
    }, []);

    return (
        <Navbar expand="lg" bg="dark" data-bs-theme="dark" className='justify-content-between'>
            <Container>
                <Navbar.Brand href='/'>
                    <img alt=''
                        src={logo}
                        width='30'
                        height='30'
                        className='d-inline-block align-top'></img>
                    Book-Loan</Navbar.Brand>
                
                <Navbar.Toggle area-controls="basic-navbar-nav" />

                <Navbar.Collapse id='basic-navbar-nav'>
                    {me && 
                    <Form onSubmit={(e) => {
                        e.preventDefault();
                        window.location.href = `/books?kw=${search}`;
                    }}>
                        <Row>
                            <Col xs="auto">
                                <Form.Control type='test'
                                placeholder='Search'
                                className='mr-sm-2' onChange={(e) => setSearch(e.target.value)} value={search}></Form.Control>
                            </Col>
                            <Col xs="auto">
                                <Button type='submit'>Search</Button>
                            </Col>
                        </Row>
                    </Form>
                    }
                    <Nav className="me-auto">
                        <Nav.Link href='/'>Home</Nav.Link>
                        {me && <Nav.Link href='/books'>Books</Nav.Link>}
                        {me && <Nav.Link href='/loans'>My Loans</Nav.Link>}
                    </Nav>
                    <Nav className='me-right'>
                        {!me && <Nav.Link href='/login'>Login</Nav.Link>}
                        {me && <Nav.Link href='/logout'>Logout</Nav.Link>}
                    </Nav>
                </Navbar.Collapse>
            </Container>

        </Navbar>
    )
}