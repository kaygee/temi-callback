<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>Cookie Service</title>
        <link href="/css/main.css" rel="stylesheet">
    </head>
    <body>
        <h1>Search the Cookie Service</h1>
        <p>Search using the Role (not the username) and the environment to
        retrieve the information for those cookies.</p>
        <#if user?? >
            Your submitted data<br>
            Name: ${roleAndEnvironment.role}<br>
            Message: ${roleAndEnvironment.environment}<br>
        <#else>
            <form action="http://localhost:7331/cookies" method="post">
                <label for="role">Role:
                <input required type="text" name="role" id="role"
                placeholder="Role"/>
                </label>
                <br/><br/>
                <label for="environment">Environment:
                <input required type="text" name="environment"
                id="environment" placeholder="Environment"></input>
                </label>
                <br/><br/>
                <input type="submit" value="Submit"/>
                <input type="reset" value="Reset"/>
            </form>
        </#if>
        <script src="/js/main.js"></script>
    </body>
</html>
