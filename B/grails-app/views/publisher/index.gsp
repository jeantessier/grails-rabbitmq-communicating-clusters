<%@ page import="b.MessageType" %>

<html>

<body>

<g:form action="send">

<div>Message: <g:textField name="messageBody"/></div>
<div>Send as:
    <g:each var="type" in="${MessageType}">
        <g:radio name="messageType" value="${type}"/>
        ${type}
    </g:each>
</div>
<div><g:submitButton name="submit" value="submit"/></div>

</g:form>

<g:if test="${flash.message}">
    <div>${flash.message}</div>
    <div><code>${flash.message.getClass().name}</code></div>
</g:if>

</body>

</html>
