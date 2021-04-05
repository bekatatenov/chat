<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/login.css">
    <title>Forum Login</title>
</head>
<body>

<div class="login-page">
    <div class="form">
        <form class="login-form" action="/login" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>

            <input type="text" name="username" placeholder="Your-Email" value="${(form.email)!''}"
                   autofocus>
            <input type="text" name="password" placeholder=You-Password" value="">
            <button>"LogIn"</button>
            <p class="message">NotRegistered? <a
                        href="/register">CreateAccount</a></p>
            <p class="message">MainPage?<a href="/">Main</a></p>
        </form>
    </div>
</div>
<script src="/js/login.js"></script>
</body>
</html>