import 'bootstrap/dist/css/bootstrap.min.css';
import MyNavbar from './components/MyNavbar';
import LoginForm from './components/LoginForm';
import { Button, Col, Container, Row } from 'react-bootstrap';
import { useState } from 'react';
import { Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import BookList from './pages/BookList';
import Logout from './pages/Logout';
import Signup from './pages/Signup';

function App() {
  const [loggedIn, setLoggedIn] = useState(false);

  return (
    <>
      <Container fluid="true">
        <Row>
          <Col>
            <MyNavbar></MyNavbar>
          </Col>
        </Row>
        {/* <Row className='justify-content-md-center' style={{display: 'flex'}}>
          <Col md="4">
            <div style={{alignSelf: 'center'}}>
              <LoginForm></LoginForm>
            </div>
          </Col>
        </Row> */}
        <Routes>
          <Route path='/' element={<Home />}></Route>
          <Route path='/login' element={<Login />}></Route>
          <Route path='/logout' element={<Logout />}></Route>
          <Route path='/signup' element={<Signup />}></Route>
          <Route path='/books' element={<BookList />}></Route>
        </Routes>
      </Container>
    </>
  )
}

export default App;
