import { useCallback } from 'react';
import { Button, Modal } from 'react-bootstrap';

const MyAlert = ({ isOpen, toggleModal, title, body}) => {
    return (
        <>
        <Modal show={isOpen} onHide={toggleModal} animation={true}>
            <Modal.Header closeButton>
                <Modal.Title>{title}</Modal.Title>
            </Modal.Header>

            <Modal.Body>
                <p>{body}</p>
            </Modal.Body>

            <Modal.Footer>
                <Button variant='primary' onClick={toggleModal} className='closeModalBtn'>
                    닫기
                </Button>
            </Modal.Footer>
        </Modal>
        </>
    )
};

export default MyAlert;