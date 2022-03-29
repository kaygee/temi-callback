<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>Cookie Service</title>
        <link href="/css/main.css" rel="stylesheet">
    </head>
    <body>
        <h1>Cookie Service Search</h1>
        <#if user?? >
            Your submitted data<br>
            Name: ${roleAndEnvironment.role}<br>
            Message: ${roleAndEnvironment.environment}<br>
        <#else>
            <form action="/form" method="post">
                <label for="role">Role:
                <input type="text" name="role" id="role"/>
                </label>
                <br/><br/>
                <label for="environment">Environment:
                <input type="text" name="environment" id="environment"></input>
                </label>
                <br/><br/>
                <input type="submit" value="Submit"/>
                <input type="reset" value="Reset"/>
            </form>
        </#if>
        <script src="/js/main.js"></script>
    </body>
</html>
