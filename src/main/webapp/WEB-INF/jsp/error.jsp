<%@ page language="java"
         contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        table td {
            vertical-align: top;
            border: solid 1px #888;
            padding: 10px;
        }
    </style>
    <title>Something went wrong...</title>
</head>
<body>
<table>
    <tr>
        <td>Data</td>
        <td>${timestamp}</td>
    </tr>
    <tr>
        <td>Błąd</td>
        <td>${error}</td>
    </tr>
    <tr>
        <td>Status</td>
        <td>${status}</td>
    </tr>
    <tr>
        <td>Wiadomość</td>
        <td>${message}</td>
    </tr>
    <tr>
        <td>Wyjątek</td>
        <td>${exception}</td>
    </tr>
    <tr>
        <td>Trace</td>
        <td>
            <pre>${trace}</pre>
        </td>
    </tr>
</table>
</body>
</html>