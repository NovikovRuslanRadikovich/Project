<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <link href="/css/signup.css"  rel="stylesheet">

    <title>signup</title>

</head>
<body>


<#if existsUser??>
<strong>${existsUser}</strong>
</#if>

    <form action="/junior-3/sign-up" method="post">
        <#if falseusername??>
         <div id="falseusername">${falseusername}</div>
        </#if>
        <label for="username">Имя пользователя</label>
        <input type="text" name="username" value=${usernameEntered!""} >
        <#if falsepassword??>
        <div id="falsepassword">${falsepassword}</div>
        </#if>
        <label for="password">Пароль</label>
        <input type="text" name="password" value=${passwordEntered!""} >
        <#if passwordsequality??>
          <div id="passwordsEquality">${passwordsequality} </div>
        </#if>
        <label for="confirmPassword">Повтор пароля</label>
        <input type="text" name="confirmPassword" value=${confirmPasswordEntered!""} >
        <input type="submit" value="Зарегистрироваться">
    </form>


</body>
</html>