<%@ include file="/jsp/include.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Stock Quote Application</title>
</head>
<body>
<h1>Stock Quote Lookup for ${stockQuote.symbol}</h1>

<p>

    <c:choose>
        <c:when test="${stockQuote.valid}">
            Current price: <fmt:formatNumber value="${stockQuote.price}" type="currency" groupingUsed=","/>
        </c:when>
        <c:otherwise>
            Stock not found
        </c:otherwise>
    </c:choose>

</p>

<p>
    <a href="getstockquote.htm">Search for quotes</a>
</p>
<p>
    <a href="viewstockquotes.htm">View old quotes</a>
</p>

</body>
</html>
