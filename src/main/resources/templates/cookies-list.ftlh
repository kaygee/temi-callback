<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Cookies List</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    </head>
    <body>
        <h1>Cookies List</h1>
        <br/><br/>
        <div>
            <table border="1">
                <tr>
                    <th>Role</th>
                    <th>Environment</th>
                    <th>Username</th>
                    <th>Created On</th>
                </tr>
                <#list cookies as cookie>
                    <tr>
                        <td>${cookie.role}</td>
                        <td>${cookie.environment}</td>
                        <td>${cookie.username}</td>
                        <td>${cookie.createdOn}</td>
                    </tr>
                </#list>
            </table>
        </div>
    </body>
</html>