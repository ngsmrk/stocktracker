<%@ include file="/jsp/include.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Stock Quote Application</title>
    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>
<body>
<h1>Stock Quote Lookup</h1>

<p>
    <form:form method="POST" commandName="quote">
        <form:errors path="*" cssClass="errorblock" element="div"/>
<table>
    <tr>
        <td>Enter stock symbol (e.g. MSFT)</td>
        <td><form:input path="symbol"/></td>
        <td><form:errors path="symbol" cssClass="error"/></td>
    </tr>
    <tr>
        <td colspan="3"><input type="submit"/></td>
    </tr>
</table>
</form:form>
</p>

<p>
    <a href="viewstockquotes.htm">View old quotes</a>
</p>

</body>
</html>
