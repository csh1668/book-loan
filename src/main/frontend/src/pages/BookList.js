import { useEffect, useState } from "react";
import BookApi from "../services/BookApi";
import BookCard from "../components/BookCard";
import { Container, Row, Col } from "react-bootstrap";
import { useSearchParams } from "react-router-dom";

const BookList = () => {
    const [books, setBooks] = useState([]);
    const [page, setPage] = useState(0);
    const [searchParams] = useSearchParams();

    useEffect(() => {
        const fetchBooks = async () => {
            const kw = searchParams.get("kw");
            console.log("kw:", kw);
            
            try {
                const response = await BookApi().getList(page, kw);
                console.log(response);
                
                setBooks(response.data.content);
            } catch (error) {
                console.error("Error fetching books:", error);
            }
        };

        fetchBooks();
    }, []);

    return (
        <>
            <Container>
                <h1>Book List</h1>
                <Row>
                    {books.map(book => (
                        <Col key={book.id} xs={12} sm={6} md={4} lg={3}>
                            <BookCard book={book}></BookCard>
                        </Col>
                    ))}
                </Row>
            </Container>
        </>
    )
}

export default BookList;