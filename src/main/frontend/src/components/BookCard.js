import { Card } from "react-bootstrap";
import placeholder from '../placeholder.svg';
import { useEffect, useState } from "react";
import BookApi from "../services/BookApi";
import MyAlertYesNo from "./MyAlertYesNo";
import LoanApi from "../services/LoanApi";


const BookCard = ({ book: { id, title, author, description }}) => {
    const [image, setImage] = useState('');
    const [loaned, setLoaned] = useState(false);
    const [toggle, setToggle] = useState(false);
    const [returnDate, setReturnDate] = useState(new Date());

    useEffect(() => {
        BookApi().fetchBookImage(id).then((resp) => {
            console.log('Image response:', resp);
            
            setImage(resp);
        });
        LoanApi().checkLoan(id).then((resp) => {
            setLoaned(resp.success);
            if (resp.success) {
                setReturnDate(new Date(resp.data.returnDate));
            }
        });
    }, []);

    const loanBook = () => {
        setLoaned(true);
        LoanApi().loanBook(id).then((resp) => {
            console.log('Loan response:', resp);
            setReturnDate(new Date(resp.data.returnDate))
        });
    };

    const returnBook = () => {
        setLoaned(false);
        LoanApi().returnBook(id).then((resp) => {
            console.log('Return response:', resp);
        });
    }

    return (
        <>
        <Card style={{ width: '18rem' }}>
            <Card.Img variant="top" src={image} />
            <Card.Body>
                <Card.Title>{title}</Card.Title>
                <Card.Subtitle className="mb-2 text-muted">
                    {author}
                </Card.Subtitle>
                <Card.Text>
                    {description}
                </Card.Text>
                {!loaned && <Card.Link href="#" onClick={() => {
                    setToggle(true);
                }}>Loan</Card.Link>}
                {loaned && <Card.Link href="#" onClick={() => {
                    setToggle(true);
                }}>Return</Card.Link>}
                {loaned && <Card.Text>{`${returnDate.getFullYear()}년 ${returnDate.getMonth() + 1}월 ${returnDate.getDate()}일까지 반납하세요.`}</Card.Text>}
            </Card.Body>
        </Card>

        {!loaned && <MyAlertYesNo isOpen={toggle} yes={() => {loanBook(); setToggle(false);}} no={() => {setToggle(false);}} title={"대출"} body={"대출하시겠습니까?"}></MyAlertYesNo>}
        {loaned && <MyAlertYesNo isOpen={toggle} yes={() => {returnBook(); setToggle(false);}} no={() => {setToggle(false);}} title={"반납"} body={"반납하시겠습니까?"}></MyAlertYesNo>}
        </>
    )
};

export default BookCard;