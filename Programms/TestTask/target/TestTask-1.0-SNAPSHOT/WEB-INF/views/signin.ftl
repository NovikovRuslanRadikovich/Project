<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="/css/signin.css" rel="stylesheet">

    <title>signin</title>
</head>
<body>

   <#if error??>
      <div class="error">Имя пользователя и пароль не подходят!</div>
   <#elseif info??>
     <div class="message"> Необходимо ввести учетные данные</div>
   <#else>
      <div class="exited">Вы вышли из приложения</div>
   </#if>
<br>
   <form action="/junior-3/sign-in" method="post">
       <label for="username"> Имя пользователя</label>
       <input type="text" name="username"  value=${username!""} >
       <br>
       <label for="password"> Пароль</label>
       <input type="password" name="password" value=${password!""} >
       <br>
       <input type="submit" value="Войти">
   </form>

   <a href="/junior-3/sign-up">Регистрация</a>
</body>
</html>