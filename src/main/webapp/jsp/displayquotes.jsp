<%@ include file="/jsp/include.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Stock Quote Application</title>
</head>
<body>
<h1>Stock Quotes</h1>

<p>

<table>
    <tr>
        <th>Symbol</th>
        <th>Most recent price</th>
    </tr>
    <c:forEach var="stockQuote" items="${quotes}">
        <tr>
            <td>${stockQuote.symbol}</td>
            <td><fmt:formatNumber value="${stockQuote.price}" type="currency" groupingUsed=","/></td>
        </tr>

    </c:forEach>
</table>

</p>

<p>
    <a href="getstockquote.htm">Search for quotes</a>
</p>

</body>
</html>
