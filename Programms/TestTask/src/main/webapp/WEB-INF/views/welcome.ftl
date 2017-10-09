<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>welcome</title>
    <link rel="stylesheet" href="/css/welcome.css">
</head>
<body>


     <#if morning??>
         Доброе утро, ${user!"anonymous"}!
      <#elseif afternoon??>
         Добрый день, ${user!"anonymous"}!
      <#elseif evening??>
         Добрый вечер, ${user!"anonymous"}!
     <#elseif night??>
         Добрая ночь, ${user!"anonymous"}!

     </#if>

     <br>
     <a  href="/junior-3/sign-in?action=logout">Выйти</a>

</body>
</html>