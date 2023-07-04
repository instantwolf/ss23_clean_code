package edu.aau.cleancode.webcrawler.parser;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JsoupHtmlParserTest {
    private JsoupHtmlParser parser;
    private Connection connectionMock;
    private Document documentMock;
    private Elements elementsMock;

    private ParseSelectorType parserType;

    @BeforeEach
    void setUp() {
        parser = new JsoupHtmlParser();
        connectionMock = mock(Connection.class);
        documentMock = mock(Document.class);
        elementsMock = mock(Elements.class);
        parserType = mock(ParseSelectorType.class);
        parser.connection = connectionMock;
    }

    @Test
    void getElements_ReturnsElementsFromDocument() throws IOException {
        URL website = new URL("http://example.com");
        List<ParseSelector> selectors = new ArrayList<>();
        selectors.add(new ParseSelector("selector1", parserType));
        selectors.add(new ParseSelector("selector2", parserType));

        when(connectionMock.method(Connection.Method.GET)).thenReturn(connectionMock);
        when(connectionMock.url(website)).thenReturn(connectionMock);
        when(connectionMock.get()).thenReturn(documentMock);
        when(documentMock.select("selector1")).thenReturn(elementsMock);
        when(documentMock.select("selector2")).thenReturn(elementsMock);

        Elements result = parser.getElements(website, selectors);

        assertEquals(elementsMock, result);
        verify(connectionMock).method(Connection.Method.GET);
        verify(connectionMock).url(website);
        verify(connectionMock).get();
        verify(documentMock).select("selector1");
        verify(documentMock).select("selector2");
    }

    @Test
    void getElements_ThrowsIOException_WhenConnectionFails() throws IOException {
        URL website = new URL("http://example.com");
        List<ParseSelector> selectors = new ArrayList<>();
        selectors.add(new ParseSelector("selector1", parserType));

        when(connectionMock.method(Connection.Method.GET)).thenReturn(connectionMock);
        when(connectionMock.url(website)).thenReturn(connectionMock);
        when(connectionMock.get()).thenThrow(IOException.class);

        assertThrows(IOException.class, () -> parser.getElements(website, selectors));
        verify(connectionMock).method(Connection.Method.GET);
        verify(connectionMock).url(website);
        verify(connectionMock).get();
        verifyNoMoreInteractions(documentMock);
    }

    @Test
    void getElements_ThrowsHttpStatusException_WhenResponseStatusIsInvalid() throws IOException {
        URL website = new URL("http://example.com");
        List<ParseSelector> selectors = new ArrayList<>();
        selectors.add(new ParseSelector("selector1", parserType));

        when(connectionMock.method(Connection.Method.GET)).thenReturn(connectionMock);
        when(connectionMock.url(website)).thenReturn(connectionMock);
        when(connectionMock.get()).thenThrow(HttpStatusException.class);

        assertThrows(HttpStatusException.class, () -> parser.getElements(website, selectors));
        verify(connectionMock).method(Connection.Method.GET);
        verify(connectionMock).url(website);
        verify(connectionMock).get();
        verifyNoMoreInteractions(documentMock);
    }
}