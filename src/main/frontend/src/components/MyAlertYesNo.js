import { useCallback } from 'react';
import { Button, Modal } from 'react-bootstrap';

const MyAlertYesNo = ({ isOpen, yes, no, title, body}) => {
    return (
        <>
        <Modal show={isOpen} onHide={no} animation={true}>
            <Modal.Header closeButton>
                <Modal.Title>{title}</Modal.Title>
            </Modal.Header>

            <Modal.Body>
                <p>{body}</p>
            </Modal.Body>

            <Modal.Footer>
                <Button variant='primary' onClick={yes} className='closeModalBtn'>
                    예
                </Button>
                <Button variant='primary' onClick={no} className='closeModalBtn'>
                    아니오
                </Button>
            </Modal.Footer>
        </Modal>
        </>
    )
};

export default MyAlertYesNo;