<!DOCTYPE HTML>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8" />
        <title>Cookies Service List</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css"/>
        <script src="/js/filter-rows.js"></script>
    </head>
    <body>
        <h1>Cookies</h1>
        <input type=text" id="filter-input" onkeyup="filterFunction()"
        placeholder="Search ${cookies?size} role(s)...">
        <div>
            <table id="cookies-table" border="1">
                <tr class="header">
                    <th>Role</th>
                    <th>Environment</th>
                    <th>Username</th>
                    <th>Remember Me</th>
                    <th>Created On</th>
                    <th>Expires On</th>
                </tr>
                <#list cookies as cookie>
                    <tr>
                        <td>${cookie.role}</td>
                        <td>${cookie.environment}</td>
                        <td>${cookie.username}</td>
                        <td>${cookie.rememberMe?string('yes', 'no')}</td>
                        <td>${cookie.createdOn}</td>
                        <td>${cookie.expiresOn}</td>
                    </tr>
                </#list>
            </table>
        </div>
    </body>
</html>