package com.nzannino.web.data;

import com.nzannino.web.data.db.tables.pojos.Book;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.nzannino.web.data.db.Tables.BOOK;

/**
 * @author Lukas Eder
 */
@Service
public class DefaultBookService implements BookService {

	@Autowired
    DSLContext dsl;

	@Transactional
	public void create(int id, int authorId, String title) {
		dsl.insertInto(BOOK).set(BOOK.ID, id).set(BOOK.AUTHOR_ID, authorId).set(BOOK.TITLE, title).execute();
	}

    public List<Book> list(){
        List<Book> bookList = new ArrayList<>();
        dsl.selectFrom(BOOK).orderBy(BOOK.ID).fetch().map(rs -> bookList.add(new Book(
                        rs.getValue(BOOK.ID),
                        rs.getValue(BOOK.AUTHOR_ID),
                        rs.getValue(BOOK.CO_AUTHOR_ID),
                        rs.getValue(BOOK.DETAILS_ID),
                        rs.getValue(BOOK.TITLE),
                        rs.getValue(BOOK.PUBLISHED_IN),
                        rs.getValue(BOOK.LANGUAGE_ID),
                        rs.getValue(BOOK.CONTENT_TEXT),
                        rs.getValue(BOOK.CONTENT_PDF),
                        rs.getValue(BOOK.REC_VERSION),
                        rs.getValue(BOOK.REC_TIMESTAMP)))
        );
        return bookList;
    }
}
