<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<footer class="border-top" style="position: absolute; bottom: 0; width: 100%">
    <div class="container text-center pt-3 mb-5">
        &copy;
        <%
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        %>
        <%=dateFormat.format(new Date())%>г.
    </div>
</footer>